package com.chat.chat_mvc_swing.client;

import com.chat.chat_mvc_swing.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BotClient extends Client {
    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            Calendar calendar = new GregorianCalendar();
            
            SimpleDateFormat date = new SimpleDateFormat("d.MM.YYYY");
            SimpleDateFormat day = new SimpleDateFormat("d");
            SimpleDateFormat month = new SimpleDateFormat("MMMM");
            SimpleDateFormat year = new SimpleDateFormat("YYYY");
            SimpleDateFormat time = new SimpleDateFormat("H:mm:ss");
            SimpleDateFormat hour = new SimpleDateFormat("H");
            SimpleDateFormat minutes = new SimpleDateFormat("m");
            SimpleDateFormat seconds = new SimpleDateFormat("s");

            ConsoleHelper.writeMessage(message);
            if (!message.contains(": ")) return;

            String name = message.split(":")[0].trim();
            String text = message.split(":")[1].trim();

            switch (text) {
                case "дата":
                    sendTextMessage("Информация для " + name + ": " + date.format(calendar.getTime()));
                    break;
                case "день":
                    sendTextMessage("Информация для " + name + ": " + day.format(calendar.getTime()));
                    break;
                case "месяц":
                    sendTextMessage("Информация для " + name + ": " + month.format(calendar.getTime()));
                    break;
                case "год":
                    sendTextMessage("Информация для " + name + ": " + year.format(calendar.getTime()));
                    break;
                case "время":
                    sendTextMessage("Информация для " + name + ": " + time.format(calendar.getTime()));
                    break;
                case "час":
                    sendTextMessage("Информация для " + name + ": " + hour.format(calendar.getTime()));
                    break;
                case "минуты":
                    sendTextMessage("Информация для " + name + ": " + minutes.format(calendar.getTime()));
                    break;
                case "секунды":
                    sendTextMessage("Информация для " + name + ": " + seconds.format(calendar.getTime()));
                    break;
            }

        }
    }
}
