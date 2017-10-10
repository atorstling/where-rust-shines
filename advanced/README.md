# Advanced excercie
This directory contains a template for a more advanced Rust application and this
excercise is intended to allow you to dive into a more realistic program and get
to experience some real-world Rust problems.

## The task
The task is modeled after how the clients for Cygni's snake competition work:
- You connect to websocket and send a message to indicate that you want to start
  receiving messages;
- The server will assign you a unique ID which you must use in all your messages;
- You will receive messages to which you are expected to respond with some
  information;
- Due to how websockets work you need to send a heartbeat message every 1 second
  to ensure that the websocket isn't killed during longer periods of down-time;
- The heart beat messages should contain the id you are assigned;
- If the heart beat messages get an error they should be able to kill the thread
  responding to messages, and vice versa;
- If the user wants to terminate the program with Ctrl-C it should terminate
  instantly and not wait for the heartbeat to "wake up".
  
## The code
There exists already a skeleton project which contains some basic code for
connecting to a websocket, passing a message and listening for messages.

However, the rest of the code it is up to you to write - especially important is
of course to choose how you let the threads communicate given that it must be
race-condition safe.

If you want inspiration, see (the actual
implementation)[https://github.com/cygni/snakebot-client-rust].
