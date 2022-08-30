import javafx.scene.control.Label;
import orad.IREConnectionModel;
import orad.IREConnectionModelListener;
import orad.retalk2.Retalk2ConnectionController;


public class Main {
    private static final Retalk2ConnectionController controller = new Retalk2ConnectionController();


    public static void main(String[] args) {
        statusRE();
        controller.setHostName("localhost");
        controller.setCanvasName("Canvas1");
        controller.connect();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        controller.sendLoadScene("Demo/Lower_3rds");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        controller.sendActivateScene("Demo/Lower_3rds", 250);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        controller.sendAnimationPlay("Demo/Lower_3rds", "_R_L3_IN");
        controller.sendSetExport("Demo/Lower_3rds", "L3_TEXT_RIGHT", "My TEXT");
    }

    private static void statusRE() {
        controller.addListener(new IREConnectionModelListener() {
            @Override
            public void statusChanged(IREConnectionModel model, IREConnectionModel.Status status) {
                switch (status) {
                    case Connecting:
                    case PreConnected:
                    case LoadingScene:
                        //reStatus.setStyle(Constants.STATUS_FONT_ORANGE);
                    case Connected:
                        //reStatus.setStyle(Constants.STATUS_FONT_GREEN);
                    case Disconnected:
                    default:
                        //reStatus.setStyle(Constants.STATUS_FONT_RED);
                }
                System.out.println(status);
            }

            @Override
            public void hostNameChanged(IREConnectionModel model, String hostname) {

            }

            @Override
            public void canvasChanged(IREConnectionModel model, String canvas) {

            }

            @Override
            public void portChanged(IREConnectionModel model, int port) {

            }

        });
    }
}
