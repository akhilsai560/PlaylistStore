package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class Store {

    Map<String, List<String>> playlistPerUser;
    private int initialCapacity;

    public Store(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.playlistPerUser = new HashMap<>();
    }

    public void recentlyPlayedSong(String user, String song) {
        List<String> userPlaylist = playlistPerUser.getOrDefault(user, new ArrayList<>());
        userPlaylist.remove(song);
        userPlaylist.add(song);
        playlistPerUser.put(user, userPlaylist);
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        List<String> userPlaylist = playlistPerUser.getOrDefault(user, emptyList());

        if (userPlaylist.size() <= initialCapacity) {
            return userPlaylist;
        }
        return userPlaylist.subList(userPlaylist.size() - initialCapacity, userPlaylist.size());
    }
}
