/*
 * Copyright (c) 2012-2014, Ing. Gabriel Barrera <gmbarrera@gmail.com>
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above 
 * copyright notice and this permission notice appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES 
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR 
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES 
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN 
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF 
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package ia.danisquierdo.mocks;

import ia.battle.core.BattleField;
import ia.battle.core.GeneralListener;
import ia.battle.core.FieldCell;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorLoader;
import ia.battle.core.WarriorManager;
import ia.battle.gui.SwingConsole;
import ia.battle.gui.sound.DefaultSoundPlayer;
import ia.battle.gui.sound.SoundPlayer;
import ia.danisquierdo.test.ElPuntero;
import ia.danisquierdo.test.TestWarriorManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BattleFieldSetup extends JFrame {

	/**
     * 
     */
	private static final long serialVersionUID = 693518024717393345L;
	private static final String SETTINGS_FILE = "Battlefield jar files.txt";
	private JLabel title;
	private JButton startFight;

	private Frame frame;
	private boolean inFight;
	private SoundPlayer soundPlayer;

	private GeneralListener battleFieldListener;
	
	public BattleFieldSetup() {

		title = new JLabel("IA - Battle Field Agents", JLabel.CENTER);
		title.setBorder(BorderFactory.createEtchedBorder());

		this.add(title, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		this.add(panel);

		startFight = new JButton("Fight!");
		startFight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				figthClicked();
			}
		});

		soundPlayer = new DefaultSoundPlayer();

		battleFieldListener = new GeneralListener() {

			@Override
			public void startFight() {

				inFight = true;

				if (frame != null) {
					frame.dispose();
					frame = null;
				}

				frame = new Frame(BattleField.getInstance(), 16, 16);
				frame.setSize(1024, 720);
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);

				frame.addWindowListener(new WindowListener() {

					@Override
					public void windowActivated(WindowEvent arg0) {
					}

					@Override
					public void windowClosed(WindowEvent arg0) {
					}

					@Override
					public void windowClosing(WindowEvent arg0) {

						if (JOptionPane.showConfirmDialog(frame, "Est√° seguro de finalizar la batalla?", "Confirme Acci√≥n",
								JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
							inFight = false;
							frame.dispose();
						}
					}

					@Override
					public void windowDeactivated(WindowEvent arg0) {
					}

					@Override
					public void windowDeiconified(WindowEvent arg0) {
					}

					@Override
					public void windowIconified(WindowEvent arg0) {
					}

					@Override
					public void windowOpened(WindowEvent arg0) {
					}

				});
			}

			public void tickLapsed(long tick) {

			}

			public void turnLapsed(long tick, int turnNumber, Warrior warrior) {
				frame.repaint();
			}

			public boolean continueFighting() {
				return inFight;
			}

			public void warriorAttacked(Warrior attacked, Warrior attacker, int damage) {
				//soundPlayer.playAttack();
			}

			@Override
			public void warriorKilled(Warrior killed) {
				//soundPlayer.playBotKilled();
			}

			@Override
			public void warriorMoved(Warrior warrior, FieldCell from, FieldCell to) {

			}

			@Override
			public void figthFinished(WarriorManager winner) {
				inFight = false;
				if (winner != null) {
					System.out.println("The winner is " + winner.getName());
					JOptionPane.showMessageDialog(null, "The winner is " + winner.getName());
				}
			}

			@Override
			public void worldChanged(FieldCell oldCell, FieldCell newCell) {
				// TODO Auto-generated method stub

			}

			@Override
			public void statsChanged(String managerName1, int warriorsKilled1, String managerName2, int warriorsKilled2) {

				frame.getFieldBoard().setWarriorManagerName1(managerName1);
				frame.getFieldBoard().setWarriorManagerName2(managerName2);
				frame.getFieldBoard().setWarriorsKilled1(warriorsKilled1);
				frame.getFieldBoard().setWarriorsKilled2(warriorsKilled2);

			}

		};
		
		this.add(startFight, BorderLayout.SOUTH);
	}

	protected void figthClicked() {

		WarriorLoader warriorLoader = new WarriorLoader();
		WarriorManager wm;

		try {

			//TODO: ac· va una clase
			wm = new ElPuntero();

			BattleField.getInstance().addWarriorManager(wm);

			//TODO: ac· va una clase
			wm = new TestWarriorManager();

			BattleField.getInstance().addWarriorManager(wm);

			BattleField.getInstance().addListener(battleFieldListener);

			startFight.setEnabled(false);

			new Thread(new Runnable() {
				@Override
				public void run() {

					BattleField.getInstance().fight();
					BattleField.getInstance().showResult();

					startFight.setEnabled(true);
				}
			}).start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingConsole.run(new BattleFieldSetup(), 600, 400, true, "IA");
	}
}
