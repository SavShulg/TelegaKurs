package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.AppPhoto;
import pro.sky.telegrambot.repository.PhotoRepository;

import java.io.IOException;

@Service
public class PhotoService {
    private final Logger logger = LoggerFactory.getLogger(PhotoService.class);

    private TelegramBot telegramBot;
    private PhotoRepository photoRepository;

    public PhotoService(TelegramBot telegramBot, PhotoRepository photoRepository) {
        this.telegramBot = telegramBot;
        this.photoRepository = photoRepository;
    }

    public AppPhoto findPhotoLastId(long chatId) {
        return photoRepository.findLastReport1(chatId);
    }

    /**
     * Сохранение фотографии в базе данных
     * Используетcя методы репозитория{@link}
     *
     * @param message
     */

    public void uploadPhoto(Message message) {
        PhotoSize[] photoSizes = message.photo();
        PhotoSize photoSize = photoSizes[photoSizes.length - 1];
        GetFileResponse getFileResponse = telegramBot.execute(new GetFile(photoSize.fileId()));
        AppPhoto photo = new AppPhoto();
        if (getFileResponse.isOk()) {
            String extension = StringUtils.getDigits(getFileResponse.file().filePath());
            photo.setExtension(extension);
            try {
                byte[] image = telegramBot.getFileContent(getFileResponse.file());
                photo.setData(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        photo.setChatId(message.chat().id());
        photoRepository.save(photo);
    }
}
