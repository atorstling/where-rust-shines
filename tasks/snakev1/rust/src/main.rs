#![allow(non_snake_case)]
#[macro_use]
extern crate log;
#[macro_use]
extern crate quick_error;
#[macro_use]
extern crate serde_derive;
#[macro_use]
extern crate serde_json;

extern crate rustc_version;
extern crate serde;
extern crate target_info;
extern crate ws;

mod maputil;
mod messages;
mod snake;
mod structs;
mod util;

use messages::{handle_inbound_msg, render_outbound_message, Inbound, Outbound};
use snake::Snake;
use std::string::String;

const DEFAULT_HOST: &'static str = "snake.cygni.se";
const DEFAULT_PORT: i32 = 80;
const DEFAULT_SNAKE_NAME: &'static str = "default-rust-snake-name";
const DEFAULT_VENUE: &'static str = "training";

#[derive(Clone, Debug)]
struct Config {
    host: String,
    port: i32,
    snake_name: String,
    venue: String,
}

quick_error! {
    #[derive(Debug)]
    pub enum ClientError {
        Message(err: serde_json::Error) {
            from()
        }

        Websocket(err: ws::Error) {
            from()
        }
    }
}

struct Client {
    out: ws::Sender,
    snake: Snake,
    config: Config,
}

fn route_msg(client: &mut Client, str_msg: &String) -> Result<(), ClientError> {
    let snake = &mut client.snake;

    match handle_inbound_msg(str_msg)? {
        Inbound::GameEnded(msg) => {
            snake.on_game_ended(&msg);
            client.out.close(ws::CloseCode::Normal)?;
        }
        Inbound::TournamentEnded(msg) => {
            snake.on_tournament_ended(&msg);
            client.out.close(ws::CloseCode::Normal)?;
        }
        Inbound::MapUpdate(msg) => {
            let m = render_outbound_message(Outbound::RegisterMove {
                direction: snake.get_next_move(&msg),
                gameTick: msg.gameTick,
                receivingPlayerId: msg.receivingPlayerId,
                gameId: msg.gameId,
            });
            client.out.send(m)?;
        }
        Inbound::SnakeDead(msg) => {
            snake.on_snake_dead(&msg);
        }
        Inbound::GameStarting(msg) => {
            snake.on_game_starting(&msg);
        }
        Inbound::PlayerRegistered(msg) => {
            println!("Successfully registered player");
            snake.on_player_registered(&msg);
            let m = render_outbound_message(Outbound::StartGame);
            client.out.send(m)?;
        }
        Inbound::InvalidPlayerName(msg) => {
            snake.on_invalid_playername(&msg);
        }
        Inbound::HeartBeatResponse(_) => {
            // do nothing
        }
        Inbound::GameLink(msg) => {
            println!("Watch game at {}", msg.url);
        }
        Inbound::GameResult(msg) => {
            println!("We got some game result!");
        }
        Inbound::UnrecognizedMessage => {
            println!("Received unrecognized message!");
        }
    };

    Ok(())
}

impl ws::Handler for Client {
    fn on_open(&mut self, _: ws::Handshake) -> ws::Result<()> {
        println!("Connection to Websocket opened");
        let m = render_outbound_message(Outbound::ClientInfo);
        self.out.send(m)?;
        let msg = render_outbound_message(Outbound::RegisterPlayer {
            playerName: self.config.snake_name.clone(),
            gameSettings: Default::default(), 
        });
        println!("Registering player with message: {:?}", msg);

        self.out.send(msg)
    }

    fn on_message(&mut self, msg: ws::Message) -> ws::Result<()> {
        if let ws::Message::Text(text) = msg {
            let route_result = route_msg(self, &text);
            match route_result {
                Err(e) => println!("Got error \'{:?}\' when routing message: {}", e, text),
                Ok(_) => (),
            }
        } else {
            println!("Unexpectedly received non-string message: {:?}", msg)
        }

        Ok(())
    }
}

fn main() {
    let config = Config {
        host: String::from(DEFAULT_HOST),
        port: DEFAULT_PORT,
        snake_name: String::from(DEFAULT_SNAKE_NAME),
        venue: String::from(DEFAULT_VENUE),
    };

    let connection_url = format!("ws://{}:{}/{}", config.host, config.port, config.venue);
    println!("Connecting to {:?}", connection_url);

    let result = ws::connect(connection_url, |out| {
        Client {
            out,
            snake: snake::Snake,
            config: config.clone(),
        }
    });
    println!("Websocket is done, result {:?}", result);
}
