package pro.sky.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Класс, представляющий волонтера
 * c занесением в БД.
 */
@Entity
@Table(name = "volunteers", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long id;              // id волонтера
    @Column(name = "chat_id", nullable = false)
    private long chatId;          // id волонтера в Telegram
    @Column(name = "name", nullable = false)
    private String name;          // имя волонтера
    @Column(name = "is_busy", nullable = false)
    private boolean isBusy;       //статус волонтёра (занят)
}