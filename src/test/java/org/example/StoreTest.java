package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StoreTest {

    @Test
    void shouldReturnEmptyPlaylistWhenUserHaveNotPlayedAnySongs() {
        Store store = new Store(2);

        List<String> actualResponse = store.getRecentlyPlayedSongs("user1");

        assertEquals(emptyList(), actualResponse);
    }

    @Test
    void shouldReturnOneSongWhenUserPlayedFirstSong() {
        Store store = new Store(1);
        String song1 = "song1";

        store.recentlyPlayedSong("user1", song1);

        List<String> actualResponse = store.getRecentlyPlayedSongs("user1");
        assertEquals(List.of(song1), actualResponse);
    }

    @Test
    void shouldReturnTwoSongsWhenUserPlayedASongAfterAnother() {
        Store store = new Store(2);
        String song1 = "song1";
        String song2 = "song2";

        store.recentlyPlayedSong("user1", song1);
        store.recentlyPlayedSong("user1", song2);

        List<String> actualResponse = store.getRecentlyPlayedSongs("user1");
        assertEquals(List.of(song1, song2), actualResponse);
    }

    @Test
    void shouldReturnOnlyRecentTwoSongsWhenUserPlayedMoreThanTwoSongs() {
        Store store = new Store(2);
        String song1 = "song1";
        String song2 = "song2";
        String song3 = "song3";

        store.recentlyPlayedSong("user1", song1);
        store.recentlyPlayedSong("user1", song2);
        store.recentlyPlayedSong("user1", song3);

        List<String> actualResponse = store.getRecentlyPlayedSongs("user1");
        assertEquals(List.of(song2, song3), actualResponse);
    }

    @Test
    void shouldReturnAllSongsWhenUserPlayedSongsLessThanInitialCapacity() {
        Store store = new Store(3);
        String song1 = "song1";
        String song2 = "song2";

        store.recentlyPlayedSong("user1", song1);
        store.recentlyPlayedSong("user1", song2);

        List<String> actualResponse = store.getRecentlyPlayedSongs("user1");
        assertEquals(List.of(song1, song2), actualResponse);
    }


    @Test
    void shouldReturnExistingSongAsRecentSongWhenUserPlayedExistingSongRecently() {
        Store store = new Store(3);
        String song1 = "song1";
        String song2 = "song2";

        store.recentlyPlayedSong("user1", song1);
        store.recentlyPlayedSong("user1", song2);
        store.recentlyPlayedSong("user1", song1);

        List<String> actualResponse = store.getRecentlyPlayedSongs("user1");
        assertEquals(List.of(song2, song1), actualResponse);
    }


    @Test
    void shouldReturnRecentPlaylistForBothUsers() {
        Store store = new Store(3);
        String song1 = "song1";
        String song2 = "song2";

        store.recentlyPlayedSong("user2", song1);
        store.recentlyPlayedSong("user1", song2);
        store.recentlyPlayedSong("user2", song1);

        List<String> user1Response = store.getRecentlyPlayedSongs("user1");
        List<String> user2Response = store.getRecentlyPlayedSongs("user2");
        assertEquals(List.of(song2), user1Response);
        assertEquals(List.of(song1), user2Response);
    }

    @Test
    void ExampleTestScenarioGivenInAsssessment(){
        Store store = new Store(3);
        String song1 = "song1";
        String song2 = "song2";
        String song3 = "song3";
        String song4 = "song4";

        store.recentlyPlayedSong("user1", song1);
        store.recentlyPlayedSong("user1", song2);
        store.recentlyPlayedSong("user1", song3);

        assertEquals(List.of(song1,song2,song3), store.getRecentlyPlayedSongs("user1"));


        store.recentlyPlayedSong("user1", song4);
        assertEquals(List.of(song2,song3,song4), store.getRecentlyPlayedSongs("user1"));

        store.recentlyPlayedSong("user1", song2);
        assertEquals(List.of(song3,song4,song2), store.getRecentlyPlayedSongs("user1"));

        store.recentlyPlayedSong("user1", song1);
        assertEquals(List.of(song4,song2,song1), store.getRecentlyPlayedSongs("user1"));

    }
}