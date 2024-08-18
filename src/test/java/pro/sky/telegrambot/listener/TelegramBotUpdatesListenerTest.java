package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import liquibase.pro.packaged.S;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pro.sky.telegrambot.constants.Constants;
import pro.sky.telegrambot.model.Report;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
@SpringBootTest
class TelegramBotUpdatesListenerTest {

    @MockBean
    private TelegramBot telegramBot;

    @Autowired
    private TelegramBotUpdatesListener telegramBotUpdatesListener;

    @Test
    public void testStartMessage() {
        String inputMessage = "/start";
        Long chatId = 1L;
        String outMessage = "Добро пожаловать!";

        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);
        when(message.text()).thenReturn(inputMessage);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(chatId);
        when(update.message()).thenReturn(message);

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        when(telegramBot.execute(argumentCaptor.capture())).thenReturn(null);
        telegramBotUpdatesListener.process(List.of(update));
        SendMessage actual = argumentCaptor.getValue();
        Assertions.assertThat(actual.getParameters().get("chat_id")).isEqualTo(chatId);
        Assertions.assertThat(actual.getParameters().get("text")).isEqualTo(outMessage);
    }

    @Test
    public void testInfoMesaage() {
        String inputMessage = "/Info";
        String outMesssage1 = "*Этот приют содержит много собак и кошек разных возрастов и пород. \n" +
                "*Вы можете стать счастливым хозяином компаньона! \n" +
                "*Общие правила безопасности на территории приюта.\n" +
                " \n" +
                "Пожалуйста, прочитайте их внимательно!\n" +
                "\n" +
                "1. Не открывайте загоны и вольеры без разрешения сотрудника приюта.\n" +
                "2. Кормить животных. Это может спровоцировать драку.\n" +
                "3. Пытаться погладить животное без разрешения сотрудника приюта.";

        String outMesssage2 = "Расписание:\n" +
                "Понедельник: " + "07:30-19:30\n" +
                "Вторник:     " + "07:30-19:30\n" +
                "Среда:       " + "07:30-19:30\n" +
                "Четверг:     " + "07:30-19:30\n" +
                "Пятница:     " + "07:30-19:30\n" +
                "Суббота:     " + "Выходной\n" +
                "Воскресенье: " + "Выходной\n";

        String outMesssage3 = "Для оформления пропуска необходимо:\n" +
                "1. Связаться с охраной по номеру +79098268334 или +79841780635\n" +
                "2. Сообщить Ф.И.О\n" +
                "3. Сообщить марку, модель, гос. номер (если Вы на машине и планируете заезжать на территорию приюта)";

        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("chat_id")).isEqualTo(1L);
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("text")).isEqualTo(outMesssage3);
    }

    @Test
    public void testAdoptMessage() {
        String inputMessage = "/Adopt an animal";
        String outMessage1 = "Правила знакомства с животным\n" +
                "\n" +
                "1. Заполнить электронную анкету потенциального владельца.\n" +
                "Анкета расположена по ссылке ...\n" +
                "Знакомство с животными происходит после одобрения анкеты.\n" +
                "\n" +
                "2. Дождаться звонка из приюта\n" +
                "Нам необходимо до 48 часов на рассмотрение анкеты.\n" +
                "\n" +
                "3. Приехать в приют и познакомиться с понравившимся животным или сделать выбор с помощью " +
                "специалистов приюта.\n" +
                "Если вы не определились с животным, которое хотите взять, то наши сотрудники могут показать " +
                "каждого животного. Просматривая их, вы определите, кого возьмете домой.\n" +
                "\n" +
                "4. Сходить на 2-3 бесплатных занятия с кинологом приюта.\n" +
                "Это необходимо для понимания правильно ли выбран тип животного, а также поможет выявить " +
                "поведенческие проблемы, готовность потенциального владельца корректировать эти " +
                "проблемы с помощью кинолога. При желании вы можете получить у кинолога уроки по воспитанию " +
                "собаки и начальной " +
                "дрессировке. Также визиты необходимы для формирования эмоциональной привязанности собаки к " +
                "потенциальному владельцу.";
        String outMessage2 = "Причины, по которым вам могут отказать и не разрешить" +
                " забрать животное из приюта:\n" +
                "* Отказ обеспечить безопасность питомца на новом месте\n" +
                "* Нестабильные отношения в семье\n" +
                "* Наличие дома большого количества животных\n" +
                "* Маленькие дети в семье\n" +
                "* Аллергия\n" +
                "* Животное забирают в подарок кому-то\n" +
                "* Животное забирают в целях использования его рабочих качеств\n" +
                "* Отсутствие регистрации и собственного жилья или его несоответствие нормам приюта";
        String outMessage3 = "Чтобы взять животное из приюта, вам необходимо подготовить " +
                "следующие документы:\n" +
                "1. Заполнить электронную анкету потенциального владельца на сайте приюта.\n" +
                "Знакомство с животными происходит после утверждения анкеты сотрудниками приюта.\n" +
                "2. Паспорт для подтверждения личности.\n" +
                "3. Двусторонний договор о передаче животного в распечатанном виде. Присылается опекуну после " +
                "утверждения анкеты.";

        String outMessage4 = "Общие рекомендации по перевозке питомцев\n" +
                "\n" +
                "Согласно рекомендациям ветеринаров, перед поездкой животное не стоит сильно кормить. Но дать перекусить " +
                "нужно.\n" +
                "\n" +
                "Если питомец тревожно переносит поездки, то пустой желудок отрицательно скажется на пищеварении. Если " +
                "хорошенько накормить, то в случае укачивания это увеличивает риск тошноты.\n" +
                "\n" +
                "Обеспечьте животному доступ свежего воздуха. Именно свежего, а не кондиционированного. Воздух, " +
                "охлажденный фреоном – не лучшее, чем стоит дышать животному. Кроме того, это увеличивает риск простуды.\n" +
                "\n" +
                "Делайте регулярные остановки, чтобы выгуливать питомца и давать ему попить. Во время остановок не " +
                "выпускайте животное резко из машины. Если ему не комфортно, он может удрать или испугаться шума дороги " +
                "и пуститься в бега. Лучше, если вы наденете не ошейник, а шлейку. Собаке не составит труда вывернуться " +
                "из обычного ошейка и убежать, если ей тревожно.\n" +
                "\n" +
                "При необходимости оставить животное в машине, нужно выбрать место в теньке. Машина очень быстро " +
                "нагревается на солнце. Оставьте окна приоткрытыми, чтобы обеспечить доступ свежего воздуха и положите " +
                "номер своего телефона под стекло, чтобы прохожие могли Вам сообщить, если увидят, что животному плохо. " +
                "В противном случае можно получить штраф за жестокое обращение с животным. А главное, сильно разволновать " +
                "своего любимца.\n" +
                "\n" +
                "Машина – закрытое пространство, и животное издает определенный запах. Запах усиливается, если питомец " +
                "сильно волнуется. Железы начинают вырабатывать определенные защитные вещества, которые источают запах. " +
                "После завершения поездки, рекомендуем обработать салон нейтрализатором запаха.";

        String outMessage5 = "Оборудование места для щенка\n" +
                "1. Место для щенка должно отвечать определенным требованиям. Прежде всего, необходимо убедиться, " +
                "что малыш защищен от сквозняков и может спокойно спать: не стоит располагать его его в коридоре или " +
                "там, где постоянно ходят.\n" +
                "2. Затем проверяют, чтобы было достаточно света и щенок может обследовать территорию.\n" +
                "3. Следует помнить, что место для собаки выбирается раз и навсегда, поэтому нужно все организовать " +
                "заранее чтобы у щенка было пространство для игр, а\n" +
                "взрослая собака могла свободно передвигаться на своем месте.\n" +
                "4. Не стоит устраивать щенка у батареи, иначе он вырастет слишком изнеженным.\n" +
                "5. Для щенка мелкой породы подойдет теплая, мягкая корзинка с подстилкой из искусственного меха, " +
                "которую можно стирать.\n" +
                "6.  Для такого щенка можно поставить пластиковое корыто с матрасиком. Это очень гигиенично, и во " +
                "время смены зубов щенок будет грызть края корыта, а не мебель.\n" +
                "Для крупного, быстро растущего щенка лучше постелить небольшой коврик.";

        String outMessage6 = "Оборудование помещения для взрослых собак.\n" +
                "\n" +
                "Помещения для содержания собак должны быть достаточно просторными, чтобы собака могла нормально стоять, " +
                "лежать, поворачиваться и сидеть.\n" +
                "\n" +
                "Помещения или клетки из нержавеющей стали для содержания собак должны иметь следующие размеры:\n" +
                "а) для крупных собак свыше (22,5 кг) - 1,2х1,8 м или 2,2 кв.м;\n" +
                "б) для средних собак (16-22 кг) - 1,2х1,5 м или 1,8 кв.м;\n" +
                "в) для мелких собак (4,5 - 16 кг) - 0,9х1,2 м или 1,1 кв.м.\n" +
                "Собак, содержащихся в клетках, необходимо выгуливать два раза в день, выгуливать в помещениях 1,2х3 м, " +
                "или выводить на прогулки на поводке не менее 20 минут два раза в день.";

        String outMessage7 = "Чтобы обустроить дом для животного с ограниченными возможностями, " +
                "воспользуйтесь следующими рекомендациями: \n" +
                " \n" +
                "1. Ухаживайте за животными с ограниченной подвижностью. К этой категории относятся собаки, " +
                "получившие травму позвоночника, потеряли конечности, утратили чувствительность лап из-за болезней " +
                "и т.д.\n" +
                "2. Для таких животных, прежде всего, необходимо обеспечить удобство передвижения по территории " +
                "постоянного проживания. Если животное волочит заднюю часть туловища, необходимо убрать с пола ковры, " +
                "которые могут затруднять передвижению. В то же время, чтобы защитить конечности от образования " +
                "мозолей, необходимо приобрести специальные фиксирующие подушечки. Специальные подгузники и " +
                "одноразовые пеленки помогут решить проблему с туалетом.\n" +
                "3. В некоторых случаях можно подобрать подходящую инвалидную коляску или ходунки для собаки " +
                "или кошки. Такие конструкции должны иметь достаточно легкий вес, но в то же время не " +
                "прогибаться под весом питомца. Крепления не должны натирать кожу или вызывать другой дискомфорт.";
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("chat_id")).isEqualTo(1L);
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("text")).isEqualTo(outMessage7);
    }

    @Test
    public void testWriteDataMessage() {
        String inputMessage = "/Write data";
        String outMessage1 = "Введите через пробел ваше имя, фамилию и телефон: ";
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("chat_id")).isEqualTo(1L);
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("text")).isEqualTo(outMessage1);
    }
    @Test
    public void testCallVolunteerMessage() {
        String inputMessage = "/Call volunteer";
        String outMessage1 = "К сожалению, я не могу помочь Вам в решении вопроса. Направляю к вам волонтера.";
        String outMessage2 = "Волонтеры уведомлены о вас." +
                " В скором времени они с вами свяжутся";
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("chat_id")).isEqualTo(1L);
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("text")).isEqualTo(outMessage2);
    }
    @Test
    public void testDogTrainerMessage() {
        String inputMessage = "/Dog trainer advice";
        String outMessage1 = "Советы кинологов по общению с собакой\n" +
                "1. Научитесь понимать язык тела собаки. Это поможет понимать потребности и эмоциональное " +
                "состояние питомца.\n" +
                "2. Начинайте обучать собаку с её первого дня в вашем доме. Особенно это важно в отношении " +
                "щенков — ведь правильное поведение гораздо проще и быстрее сформировать с нуля, чем корректировать уже сформировавшееся.\n" +
                "3. Следите за здоровьем питомца. Проводите ежегодную диспансеризацию вашего питомца, а " +
                "начиная с 8 лет — дважды в год. Следите за тем, чтобы любимец был обработан от внешних и " +
                "внутренних паразитов.\n" +
                "4. Гуляйте вместе с собакой. Общайтесь с ней, играйте, вместе изучайте то, что её заинтересовало.\n" +
                "5. Дрессируйте вашего любимца. Дрессировка — это кропотливое создание уникальной коммуникации " +
                "между вами и вашей собакой.";
        String outMessage2 = "Список проверенных кинологов, к которым вы всегда можете обратится\n" +
                "Савелий Шульгин    - +79098268334\n" +
                "Лямзин Андрей  - +79841780635\n";
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("chat_id")).isEqualTo(1L);
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("text")).isEqualTo(outMessage2);
    }
    @Test
    public void testDogMessage() {
        String inputMessage = "/Dogs";
        String outMessage = "Собака - лучший друг человека!";
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("chat_id")).isEqualTo(1L);
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("text")).isEqualTo(outMessage);
    }
    @Test
    public void testCatMessage() {
        String inputMessage = "/Cats";
        String outMessage = "Кошки милые, уважаем ваш выбор!";
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("chat_id")).isEqualTo(1L);
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("text")).isEqualTo(outMessage);
    }
    @Test
    public void testReportHelpMessage() {
        String inputMessage = "/Report help";
        String outMessage = "\"Пришлите фотографию питомца, и заполните отчет по примеру \n " +
                "\"Утром- корм, обед-макароны, полдник-жидкий корм,ужин-косточка, перед сном-молоко \n " +
                "\"Бобик находится в состоянии адаптации \n " +
                "\"Самочувствие игривое, потихоньку изучает квартиру, боится далеко отходить от конуры \n " +
                "\"Новых привычек не обнаружено\n";
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("chat_id")).isEqualTo(1L);
        Assertions.assertThat(testHandlerByChatMassage(inputMessage).getParameters().get("text")).isEqualTo(outMessage);
    }


    private SendMessage testHandlerByChatMassage(String testMessage) {
        Long chatId = 1L;
        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);
        when(message.text()).thenReturn(testMessage);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(chatId);
        when(update.message()).thenReturn(message);

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        when(telegramBot.execute(argumentCaptor.capture())).thenReturn(null);
        telegramBotUpdatesListener.process(List.of(update));
        SendMessage actual = argumentCaptor.getValue();
        return actual;
    }
}