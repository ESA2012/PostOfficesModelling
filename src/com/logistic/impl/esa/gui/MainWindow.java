package com.logistic.impl.esa.gui;

import com.logistic.impl.model.post.PackageImproved;
import com.logistic.impl.service.SenderServiceImproved;

import javax.swing.*;
import java.awt.*;



/**
 * Created by SnakE on 06.11.2015.
 */
public class MainWindow {

    GraphPanel graphPanel;
    JRadioButton radioButtonNodesInfoIndex;
    JRadioButton radioButtonNodesInfoPacks;
    JCheckBox checkBoxShowDirections;
    JCheckBox checkBoxShowAllTransits;
    JCheckBox checkBoxShowMessages;
    DefaultListModel<PackageImproved> modelPackages;
    JList<PackageImproved> listPackages;
    JButton buttonGeneratePackage;
    JButton buttonCheckPackage;
    JButton buttonSerialize;
    JButton buttonDeserialize;
    JButton buttonGenerateGraph;
    JMenuItem menuItemAddNode;
    JMenuItem menuItemAddEdge;


    public MainWindow(SenderServiceImproved service) {
        JPanel content = new JPanel();

        MainWindowEvents actions = new MainWindowEvents(this, service);

        content.setLayout(null);

        // Radio buttons
        radioButtonNodesInfoIndex = new JRadioButton("Отображать индексы");
        radioButtonNodesInfoIndex.setBounds(720, 10, 220, 24);
        radioButtonNodesInfoIndex.addActionListener(actions);
        radioButtonNodesInfoIndex.setSelected(true);

        radioButtonNodesInfoPacks = new JRadioButton("Отображать принимаемые грузы");
        radioButtonNodesInfoPacks.setBounds(720, 40, 220, 24);
        radioButtonNodesInfoPacks.addActionListener(actions);

        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonNodesInfoIndex);
        group.add(radioButtonNodesInfoPacks);

        content.add(radioButtonNodesInfoIndex);
        content.add(radioButtonNodesInfoPacks);

        // CheckBox Show Directions
        checkBoxShowDirections = new JCheckBox("Отображать направления");
        checkBoxShowDirections.setBounds(720, 70, 220, 24);
        checkBoxShowDirections.addActionListener(actions);
        content.add(checkBoxShowDirections);

        // Package list
        modelPackages = new DefaultListModel<PackageImproved>();
        listPackages = new JList<>(modelPackages);
        ScrollPane scroll = new ScrollPane();
        scroll.setBounds(720, 100, 220, 200);
        listPackages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPackages.addListSelectionListener(actions);
        scroll.add(listPackages);
        content.add(scroll);

        // CheckBox Show Directions
        checkBoxShowAllTransits = new JCheckBox("Отображать все доступные пути");
        checkBoxShowAllTransits.setBounds(720, 310, 220, 24);
        checkBoxShowAllTransits.setSelected(true);
        checkBoxShowAllTransits.addActionListener(actions);
        content.add(checkBoxShowAllTransits);

        // Generate package button
        buttonGeneratePackage = new JButton("Генерировать посылку");
        buttonGeneratePackage.setBounds(720, 340, 220, 24);
        buttonGeneratePackage.addActionListener(actions);
        content.add(buttonGeneratePackage);

        // Check package button
        buttonCheckPackage = new JButton("Проверить ");
        buttonCheckPackage.setBounds(720, 370, 220, 24);
        buttonCheckPackage.addActionListener(actions);
        content.add(buttonCheckPackage);

        // CheckBox Show Messages
        checkBoxShowMessages = new JCheckBox("Показывать сообщение");
        checkBoxShowMessages.setBounds(720, 400, 220, 24);
        checkBoxShowMessages.setSelected(true);
        checkBoxShowMessages.addActionListener(actions);
        content.add(checkBoxShowMessages);

        // Generate graph
        buttonGenerateGraph = new JButton("Генерировать новый граф");
        buttonGenerateGraph.setBounds(720, 540, 220, 24);
        buttonGenerateGraph.addActionListener(actions);
        content.add(buttonGenerateGraph);

        // Serialize button
        buttonSerialize = new JButton("Сохранить...");
        buttonSerialize.setBounds(720, 570, 105, 24);
        buttonSerialize.addActionListener(actions);
        content.add(buttonSerialize);

        // Deserialize button
        buttonDeserialize = new JButton("Загрузить...");
        buttonDeserialize.setBounds(835, 570, 105, 24);
        buttonDeserialize.addActionListener(actions);
        content.add(buttonDeserialize);

        // PopUpMenu
        JPopupMenu popupMenuGraph = new JPopupMenu();
        menuItemAddNode = new JMenuItem("Добавить почтовое отделение...");
        menuItemAddEdge = new JMenuItem("Добавить связь...");
        menuItemAddEdge.addActionListener(actions);
        menuItemAddNode.addActionListener(actions);
        popupMenuGraph.add(menuItemAddNode);
        popupMenuGraph.add(menuItemAddEdge);

        // GraphPanel
        graphPanel = new GraphPanel();
        graphPanel.setComponentPopupMenu(popupMenuGraph);
        graphPanel.addMouseListener(new GraphMouseListener(graphPanel));
        JScrollPane graphscroll = new JScrollPane(graphPanel);
        graphscroll.setBounds(10, 10, 700, 600);
        content.add(graphscroll);

        // MainWindow
        JFrame frame = new JFrame("Граф");
        frame.setContentPane(content);
        frame.setSize (970, 670);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public GraphPanel getGraphPanel() {
        return graphPanel;
    }

}
