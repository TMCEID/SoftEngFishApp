// src/ui/FriendsPanel.java

import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FriendsPanel extends JPanel {

    private final FriendsController ctrl = new FriendsController();
    private final int ownerId;                 // current logged-in user

    /* widgets */
    private final JTextField searchBox   = new JTextField(12);
    private final JButton    btnAdd      = new JButton("➕  add");
    private final DefaultListModel<User> model = new DefaultListModel<>();
    private final JList<User>            list  = new JList<>(model);

    public FriendsPanel(int ownerId) {
        this.ownerId = ownerId;
        buildUi();
        refresh();                            // show current list on load
    }

    // build

    private void buildUi() {
        setLayout(new BorderLayout(10,10));

        /* top bar – search & add */
        var top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Phone / username:"));
        top.add(searchBox);
        top.add(btnAdd);
        add(top, BorderLayout.NORTH);

        /* center – list with avatar & username */
        list.setCellRenderer((lst, user, i, sel, foc) -> {
            var p = new JPanel(new BorderLayout());
            p.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));

            var icon = new JLabel(user.getAvatar());
            var name = new JLabel(user.getUsername() +
                    "  (" + user.getPhone() + ')');

            name.setFont(name.getFont().deriveFont(15f));
            p.add(icon , BorderLayout.WEST);
            p.add(name , BorderLayout.CENTER);
            if (sel) p.setBackground(new Color(225,230,255));
            return p;
        });
        add(new JScrollPane(list), BorderLayout.CENTER);

        /* wiring */
        btnAdd.addActionListener(e -> {
            var token = searchBox.getText().trim();
            if (token.isEmpty()) return;
            switch (ctrl.add(token, ownerId)) {
                                case ADDED ->
                                                JOptionPane.showMessageDialog(this, "Friend added!");
                                case ALREADY ->
                                                JOptionPane.showMessageDialog(this, "Already friends");
                                case NOT_FOUND ->
                                                JOptionPane.showMessageDialog(this, "User not found");
                            }
            searchBox.setText("");
            refresh();
        });
    }

    // helpers

    private void refresh() {
        List<User> friends = ctrl.getAll(ownerId);
        model.clear();
        friends.forEach(model::addElement);
    }
}
