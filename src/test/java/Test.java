import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

/**
 * @author bubble
 * @version 1.0
 * @profile :
 * @date 2023/2/1 12:56
 */
public class Test implements HotkeyListener{
    public static void main(String[] args) throws InterruptedException {
        Test demo = new Test();
        demo.init();
        JIntellitype.getInstance().registerHotKey(1, JIntellitype.MOD_SHIFT, (int)'A'); //绑定热键 shift A


        Thread.sleep(1000*10);
        JIntellitype.getInstance().cleanUp();
        System.exit(0);

    }


    void init(){
        JIntellitype.getInstance().addHotKeyListener(this);
        System.out.println(" 初始化成功 ... ");
    }
    // listen for hotkey
    public void onHotKey(int aIdentifier) {
        if (aIdentifier == 1)
            System.out.println("shift A is pressed " + aIdentifier);
    }

}
