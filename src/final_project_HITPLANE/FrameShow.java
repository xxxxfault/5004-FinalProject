package final_project_HITPLANE;

import javax.swing.*;
import java.awt.*;

public class FrameShow {
    /**
     *  * frame中的控件自适应frame大小：改变大小位置和字体
	 * @param frame 要控制的窗体
	 * @param
	 */
    public static void modifyComponentSize(JFrame frame, float proportionW, float proportionH){

        try
        {
            Component[] components = frame.getRootPane().getContentPane().getComponents();
            for(Component co:components)
            {
//				String a = co.getClass().getName();//获取类型名称
//				if(a.equals("javax.swing.JLabel"))
//				{
//				}
                float locX = co.getX() * proportionW;
                float locY = co.getY() * proportionH;
                float width = co.getWidth() * proportionW;
                float height = co.getHeight() * proportionH;
                co.setLocation((int)locX, (int)locY);
                co.setSize((int)width, (int)height);
                int size = (int)(co.getFont().getSize() * proportionH);
                Font font = new Font(co.getFont().getFontName(), co.getFont().getStyle(), size);
                co.setFont(font);
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
