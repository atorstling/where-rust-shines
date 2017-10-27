use structs::MapUpdate;
use maputil::Direction;

pub struct Snake;

impl Snake {
    pub fn get_next_move(&self, msg: &MapUpdate) -> Direction {
        let map = &msg.map;
        let snake = map.get_snake_by_id(&msg.receivingPlayerId).unwrap();

        for &d in [
            Direction::Down,
            Direction::Left,
            Direction::Right,
            Direction::Up,
        ].into_iter()
        {
            if map.can_snake_move_in_direction(snake, d) {
                println!("Snake will move in direction {:?}", d);
                return d;
            }
        }
        println!("Snake cannot but will move down.");
        return Direction::Down;
    }
}
