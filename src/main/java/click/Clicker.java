package click;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;

/**
 * @author bubble
 * @version 1.0
 * @profile :
 * @date 2023/2/1 12:56
 * shift A 获取当前鼠标位置  shift B 开启连点   shift C停止连点
 * <p>
 *  TODO Demo里的安全性检查 、 解绑按键 、复用Thread，而不是每次都新建一个线程。
 *  Question
 *      1. 停止后，按键没有解绑，可能会影响输入。
 *      2. 需要一个退出程序。
 */
public class Clicker implements HotkeyListener {

    final static int GET_LOCATION = 1;
    final static int START_CLICK = 2;
    final static int STOP_CLICK = 3;

    private Robot robot;
    Thread thread;
    volatile boolean isWork = false;

    @Override
    public void onHotKey(int identifier) {
        if (identifier == GET_LOCATION) {

        } else if (identifier == START_CLICK) {
            if (isWork) {
                System.out.println(" clicker is work");
            }
            System.out.println(" start click ");
            isWork = true;
            thread = new Thread(() -> {
                while (isWork) {
                    starClick(InputEvent.BUTTON1_DOWN_MASK, 500);
                }
            });
            thread.start();

        } else if (identifier == STOP_CLICK) {
            thread.interrupt(); //中断线程
            isWork = false;
            System.out.println(" stop click ");
        }
    }

    public void starClick(int mouseButton, int delay) {
        robot.mousePress(mouseButton);
        robot.mouseRelease(mouseButton);
        robot.delay(delay);

    }

    public void init() throws AWTException {
        JIntellitype.getInstance().addHotKeyListener(this); //创建JIntellitype
        robot = new Robot();
        JIntellitype.getInstance().registerHotKey(GET_LOCATION, JIntellitype.MOD_SHIFT, (int) 'A'); //绑定热键 shift A
        JIntellitype.getInstance().registerHotKey(START_CLICK, JIntellitype.MOD_SHIFT, (int) 'B');
        JIntellitype.getInstance().registerHotKey(STOP_CLICK, JIntellitype.MOD_SHIFT, (int) 'C');
        System.out.println(" register success");
    }

    public static void main(String[] args) throws AWTException, IOException {
        new Clicker().init();
        System.in.read();
    }
}
