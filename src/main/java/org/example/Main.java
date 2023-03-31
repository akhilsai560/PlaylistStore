package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the initial capacity for the store : ");
        int initialCapacity = scanner.nextInt();

        Store store = new Store(initialCapacity);

        boolean exit = false;

        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Add song to playlist");
            System.out.println("2. Fetch recent playlist");
            System.out.println("3. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter user name: ");
                    String user = scanner.next();
                    System.out.print("Enter song name: ");
                    String song = scanner.next();
                    store.recentlyPlayedSong(user, song);
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    user = scanner.next();
                    List<String> recentPlaylist = store.getRecentlyPlayedSongs(user);
                    System.out.println("Recent playlist for " + user + ": " + recentPlaylist);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
