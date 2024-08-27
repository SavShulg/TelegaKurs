package pro.sky.telegrambot.model;

import liquibase.pro.packaged.I;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "photo")
public class AppPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;

    private byte[] data;
    private String extension;

    public AppPhoto(Long id, Long chatId, byte[] data, String extension) {
        this.id = id;
        this.chatId = chatId;
        this.data = data;
        this.extension = extension;
    }

    public AppPhoto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppPhoto appPhoto = (AppPhoto) o;
        return Objects.equals(id, appPhoto.id) && Objects.equals(chatId, appPhoto.chatId) && Arrays.equals(data, appPhoto.data) && Objects.equals(extension, appPhoto.extension);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, chatId, extension);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "AppPhoto{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", data=" + Arrays.toString(data) +
                ", extension='" + extension + '\'' +
                '}';
    }
}
