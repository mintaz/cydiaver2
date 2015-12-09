
package jWhiteBoard;

import org.jgroups.util.Streamable;

import java.io.DataInput;
import java.io.DataOutput;

/**
 * Encapsulates information about a draw command.
 * Used by the {@link JWhiteBoard} and other demos.
 *
 */
public class DrawCommand implements Streamable {
    static final byte DRAW=1;
    static final byte CLEAR=2;
    static final byte TEXT = 3; // bien lenh TEXT
    byte mode;
    int x;
    int y;
    int rgb;
    int brushSize;
    String textMessage;
 // ham lay mess
    public String getTextMessage() {
        return textMessage;
    }
 
    // ham set mess
    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public DrawCommand() { // needed for streamable
    }

    DrawCommand(byte mode) {
        this.mode=mode;
    }
    // ham draw mes
    DrawCommand(byte mode, String textMessage) {
        this.mode = mode;
        this.textMessage = textMessage;
    }

    DrawCommand(byte mode, int x, int y, int rgb, int brushSize) {
        this.mode=mode;
        this.x=x;
        this.y=y;
        this.rgb=rgb;
        this.brushSize = brushSize;
    }


    public void writeTo(DataOutput out) throws Exception {
        out.writeByte(mode);
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(rgb);
        out.writeInt(brushSize);
        out.writeChars(textMessage);
    }

    public void readFrom(DataInput in) throws Exception {
        mode=in.readByte();
        x=in.readInt();
        y=in.readInt();
        rgb=in.readInt();
        brushSize = in.readInt();
        textMessage = in.readLine();
    }


    public String toString() {
        StringBuilder ret=new StringBuilder();
        switch(mode) {
            case DRAW: ret.append("DRAW(" + x + ", " + y + ") [" + rgb + "]");
                break;
            case CLEAR: ret.append("CLEAR");
                break;
            default:
                return "<undefined>";
        }
        return ret.toString();
    }

}
