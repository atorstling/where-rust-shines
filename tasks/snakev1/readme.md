# Writing your own Snakebot client
This exercise will show how it is to work on a somewhat larger Rust project and
to try writing without too much restrictions.

The code is a very stripped-down version of the current snakebot client with
only the most relevant code (and some additional util functions that you shouldn't
care too much about).

For this project there is no relevant Java-code to look at, so just jump straight
into the Rust code!

## Part 1
The basic functions of the client are implemented already in `main.rs`:
- The websocket connects to the correct address and sends the initial messages
  required to start the game (`ClientInfo` and `RegisterPlayer`);
- All messages have matching structs
- Incoming messages can be parsed with the (just a bit magical) function
  `handle_inbound_msg`;
- Outgoing messages can be constructed with the function `render_outbound_messages`.

However, we haven't implemented the `route_msg` function yet!

### Routing the messages
First of all find out what message we just received.

Things you might need:
- [match](https://doc.rust-lang.org/1.6.0/book/patterns.html).

### Sending a start game message
With the routing in place add, respond to the `Inbound::PlayerRegistered` message
with an empty `Outbound::StartGame` message.

Things you might need:
- `render_outbound_message`;
- `ws::Sender::send`, see the [documentation for the websocket library](https://ws-rs.org/docs);
- the `?` operator, see [this post for an example](https://m4rw3r.github.io/rust-questionmark-operator).

### Responding with a direction
When you got the routing set up it is time to respond to the `MapUpdate` event with
the `Outbound::RegisterMove` message.

Things you might need:
- `snake.get_next_move`

### Closing the socket
Finally, when we receive the `Inbound::GameEnded` message, close the websocket down
normally.

Things you might need:
- `ws::Sender::close`, see the [documentation for the websocket library](https://ws-rs.org/docs).

### Bonus: `GameLink`
If you got time, then print the url for the game, this information can be found
in the message.

## Part 2
Part 2 will focus on the multi-threaded aspect of the websocket client, with the
goal of having a working heartbeat at the end. The heartbeat is necessary to
ensure that the websocket connection doesn't close down prematurely during
periods of silence in tournaments.

This part is harder, but should be doable with the knowledge you have in hand!

### Running the websocket in a separate thread
The first step to having a heartbeat is to make the websocket run in a separate
thread. Make sure that the program also waits for the thread to terminate before
actually exiting the program.

Things you might need:
- ?

### Using channels to pass information from the websocket-thread
The heartbeat's messages will contain the userid given to us when the player
is confirmed to be registered. So the next step is to allow the websocket-thread
to communicate by having it use a channel to send the userid when it is received.
As an initial step, just listen for it in the main function and print it to the
console to verify it works.

### Start another thread that waits for the userid
Almost there... Now start another thread (the heartbeat one) that waits for the
userid from the websocket-thread and prints it. Now ensure that the main program
waits for both of these threads to exit before terminating.

### Finally: send the heartbeat
Finally, make the heartbeat thread get a reference to the websocket sender so
that it can send messages. Then every 2nd second, send a heartbeat message to the
server. Easy, right?
