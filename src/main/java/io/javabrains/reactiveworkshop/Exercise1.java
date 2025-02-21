package io.javabrains.reactiveworkshop;

import java.util.Optional;

public class Exercise1 {

  public static void main(String[] args) {

    // Use StreamSources.intNumbersStream() and StreamSources.userStream()

    // Print all numbers in the intNumbersStream stream
    StreamSources.intNumbersStream()
        .forEach(System.out::println);

    // Print numbers from intNumbersStream that are less than 5
    StreamSources.intNumbersStream()
        .filter(i -> i < 5)
        .forEach(System.out::println);

    // Print the second and third numbers in intNumbersStream that's greater than 5
    StreamSources.intNumbersStream()
        .filter(i -> i > 5)
        .skip(1)
        .limit(2)
        .forEach(System.out::println);

    //  Print the first number in intNumbersStream that's greater than 5.
    //  If nothing is found, print -1
    StreamSources.intNumbersStream()
        .filter(i -> i > 5)
        .limit(1)
        .map(Optional::of)
        .map(opt -> opt.orElse(-1))
        .forEach(System.out::println);

    Integer i = StreamSources.intNumbersStream()
        .findFirst()
        .orElse(-1);
    System.out.println(i);

    // Print first names of all users in userStream
    StreamSources.userStream()
        .map(User::getFirstName)
        .forEach(System.out::println);

    // Print first names in userStream for users that have IDs from number stream
    StreamSources.userStream()
        .filter(u -> StreamSources.intNumbersStream().toList().contains(u.getId()))
        .map(User::getFirstName)
        .forEach(System.out::println);

    StreamSources.userStream()
        .filter(u -> StreamSources.intNumbersStream().anyMatch(id -> id == u.getId()))
        .forEach(System.out::println);

    StreamSources.intNumbersStream()
        .flatMap(id -> StreamSources.userStream().filter(u -> u.getId() == id))
        .map(User::getFirstName)
        .forEach(System.out::println);
  }

}
