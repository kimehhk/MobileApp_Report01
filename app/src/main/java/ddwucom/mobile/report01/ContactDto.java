package ddwucom.mobile.report01;

import java.io.Serializable;

public class ContactDto implements Serializable {

    private long id;
    private String name;
    private String music_title;
    private String artist;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusic_title() {
        return music_title;
    }

    public void setMusic_title(String music_title) {
        this.music_title = music_title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "ContactDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", music_title='" + music_title + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
