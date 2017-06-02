package it.liceoarzignano.vote.ui;

import com.bulenkov.darcula.DarculaLaf;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import it.liceoarzignano.vote.data.io.DataLoader;
import it.liceoarzignano.vote.data.model.Room;
import it.liceoarzignano.vote.ui.components.VoteFrame;
import it.liceoarzignano.vote.ui.contracts.VoteFormContract;

import javax.swing.*;
import java.awt.*;

public class VoteForm extends JDialog {
    private JPanel panel;
    private JLabel roomLabel;
    private JList<String> checkList;
    private JProgressBar votesProgressBar;
    private JLabel votesProgressLabel;
    private JButton button;

    public VoteForm(int selected, int size) {
        try {
            UIManager.setLookAndFeel(new DarculaLaf());
        } catch (final UnsupportedLookAndFeelException ignored) {
        }

        VoteFrame frame = new VoteFrame(panel);

        Room room = new DataLoader().loadRoomsWithTeachers().get(selected);
        VoteFormContract contract = new VoteFormContract(room, size);
        contract.setup(frame, roomLabel, checkList, button,
                votesProgressBar, votesProgressLabel);

        SwingUtilities.updateComponentTreeUI(frame);
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(5, 6, new Insets(10, 10, 10, 10), -1, -1));
        roomLabel = new JLabel();
        roomLabel.setFocusable(false);
        roomLabel.setFont(new Font("Noto Sans CJK JP Medium", Font.PLAIN, 36));
        roomLabel.setText("Label");
        panel.add(roomLabel, new GridConstraints(1, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button = new JButton();
        button.setText("Vota");
        button.setMnemonic('V');
        button.setDisplayedMnemonicIndex(0);
        panel.add(button, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(32, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(0, -1), new Dimension(16, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(0, -1), new Dimension(16, -1), null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel.add(spacer3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(0, -1), new Dimension(0, 8), new Dimension(-1, 8), 0, false));
        final Spacer spacer4 = new Spacer();
        panel.add(spacer4, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(0, -1), new Dimension(0, 8), new Dimension(-1, 8), 0, false));
        votesProgressBar = new JProgressBar();
        panel.add(votesProgressBar, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        votesProgressLabel = new JLabel();
        votesProgressLabel.setFont(new Font("Noto Sans CJK JP Regular", votesProgressLabel.getFont().getStyle(), votesProgressLabel.getFont().getSize()));
        votesProgressLabel.setText("Label");
        panel.add(votesProgressLabel, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkList = new JList();
        panel.add(checkList, new GridConstraints(2, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(192, 128), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
