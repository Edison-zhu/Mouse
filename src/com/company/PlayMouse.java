package com.company;
import sun.swing.ImageIconUIResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PlayMouse extends JFrame implements Runnable{
    private JLabel back;
    private JLabel [] mouses;
     private ImageIcon imgMouse;
     private JLabel jtf;
     private int num=0;

     public PlayMouse(){
         this.setResizable(false);
         this.getContentPane().setLayout(null);
         this.setTitle("我的打地猪游戏");
         this.setBounds(300,100,500,400);

         //初始化图片
         back = new JLabel();
         ImageIcon icon =  new ImageIcon(this.getClass().getResource("3.png"));
         back.setIcon(icon);
         back.setBounds(0,-35,500,400);
         //加载锤子图片
         //this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("src/com/company/5.png"),new Point(),"self"));


         //加载老鼠图片
         imgMouse = new ImageIcon(this.getClass().getResource("4.png"));
         mouses = new JLabel[9];
         for (int i=0;i<9;i++){
             mouses[i]=new JLabel();
             mouses[i].setSize(imgMouse.getIconWidth(), imgMouse.getIconHeight());
             //mouses[i].setIcon(imgMouse);
             mouses[i].addMouseListener(new MouseAdapter() {
                 @Override
                 public void mouseClicked(MouseEvent e) {//加分功能
                     Object object=e.getSource();
                     if (object instanceof JLabel){
                         JLabel label =(JLabel) object;
                         if (label.getIcon()!=null){
                            num++;
                            jtf.setText("您的得分是:"+ num+"分");
                         }
                         label.setIcon(null);
                     }
                 }
             });
             this.getContentPane().add(mouses[i]);
         }

         jtf = new JLabel();
         jtf.setBounds(260,10,230,60);
         jtf.setFont(new Font("",20,25));
         jtf.setForeground(Color.blue);
         jtf.setText("您的得分是:   分");

         mouses[0].setLocation(88,53);
         mouses[1].setLocation(240,53);
         mouses[2].setLocation(390,53);
         mouses[3].setLocation(55,160);
         mouses[4].setLocation(245,160);
         mouses[5].setLocation(430,160);
         mouses[6].setLocation(36,296);
         mouses[7].setLocation(330,296);
         mouses[8].setLocation(445,53);
         this.getContentPane().add(jtf);
         this.getContentPane().add(back);

         this.setVisible(true);

     }
    @Override
    public void run() {
         while (true){
             try {
                 Thread.sleep(500);
                 int index = (int)(Math.random()*9);
                 if (mouses[index].getIcon()==null){
                    mouses[index].setIcon(imgMouse);
                    Thread.sleep(900);
                    if (mouses[index].isShowing()){
                        mouses[index].setIcon(null);
                    }
                 }
             }catch (InterruptedException e){
                e.printStackTrace();
             }

         }

    }
    public static void main(String[]args){
        PlayMouse P1=new PlayMouse();
        Thread t1=new  Thread(P1);
        t1.start();
    }
}
