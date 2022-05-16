##Conway's game of life implementation in Java. 
This is iteration 1.
There are 2 implementations, one is a direct and procedural implementation, 
the other tries to model the board. 

To setup

```sh
./gradlew clean build
```

To test

```sh
./gradlew test 
```

To run

```sh
./gradlew run
```

#### TODO
* The modelling looks ok, but exposing `getNeighbours` on Cells is icky
* Setters for properties that are lists is not clean. 
* These aboveare cases where objects can go into invalid state, code should not allow this to happen.
