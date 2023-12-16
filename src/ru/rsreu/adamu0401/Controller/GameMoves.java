package ru.rsreu.adamu0401.Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import ru.rsreu.adamu0401.Model.Path;
import ru.rsreu.adamu0401.View.Build_Player;
import ru.rsreu.adamu0401.View.Layout;
import ru.rsreu.adamu0401.View.Pawn;

public class GameMoves extends JPanel implements KeyListener, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	Layout la;
	Build_Player p;
	Timer time;
	int delay = 10;

	public int getCurrent_player() {
		return current_player;
	}

	int current_player;
	int dice;
	int flag = 0;
	int roll;
	int kill = 0;
	public Path path;

	public GameMoves(Path path) {
		this.setFocusTraversalKeysEnabled(false);
		this.requestFocus();
		this.current_player = 0;
		this.la = new Layout(80, 50);
		this.p = new Build_Player(this.la.height, this.la.width);
		this.dice = 0;
		this.flag = 0;
		this.roll = 0;
		this.kill = 0;
		this.path = path;
	}

	public void paint(Graphics g) {
		this.la.draw((Graphics2D)g);
		this.p.draw((Graphics2D)g);
		this.updateGame(g);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10 && this.flag == 0) {
			this.roll = 0;
			this.dice = 1 + (int)(Math.random() * 6.0);
			this.repaint();

			int i;
			for(i = 0; i < 4; ++i) {
				if (this.p.pl[this.current_player].pa[i].current != -1 && this.p.pl[this.current_player].pa[i].current != 56 && this.p.pl[this.current_player].pa[i].current + this.dice <= 56) {
					this.flag = 1;
					break;
				}
			}

			if (this.flag == 0 && this.dice == 6) {
				for(i = 0; i < 4; ++i) {
					if (this.p.pl[this.current_player].pa[i].current == -1) {
						this.flag = 1;
						break;
					}
				}
			}
		}

	}

	public void mouseClicked(MouseEvent e) {
		if (this.flag == 1) {
			int x = e.getX();
			int y = e.getY();
			x -= 80;
			y -= 50;
			x /= 30;
			y /= 30;
			int value = -1;
			int i;
			int hou;
			int j;
			int tem1;
			int tem2;
			boolean k;
			Pawn var10000;
			if (this.dice == 6) {
				for(i = 0; i < 4; ++i) {
					if (this.p.pl[this.current_player].pa[i].x == x && this.p.pl[this.current_player].pa[i].y == y && this.p.pl[this.current_player].pa[i].current + this.dice <= 56) {
						value = i;
						this.flag = 0;
						break;
					}
				}

				if (value == -1) {
					for(i = 0; i < 4; ++i) {
						if (this.p.pl[this.current_player].pa[i].current == -1) {
							this.p.pl[this.current_player].pa[i].current = 0;
							this.flag = 0;
							break;
						}
					}
				} else {
					var10000 = this.p.pl[this.current_player].pa[value];
					var10000.current += this.dice;
					if (this.p.pl[this.current_player].pa[value].current == 56) {
						++this.p.pl[this.current_player].coin;
					}

					k = false;
					hou = this.p.pl[this.current_player].pa[value].current;
					if (hou % 13 != 0 && hou % 13 != 8 && hou < 51) {
						for(i = 0; i < 4; ++i) {
							if (i != this.current_player) {
								for(j = 0; j < 4; ++j) {
									tem1 = this.path.ax[this.current_player][this.p.pl[this.current_player].pa[value].current];
									tem2 = this.path.ay[this.current_player][this.p.pl[this.current_player].pa[value].current];
									if (this.p.pl[i].pa[j].x == tem1 && this.p.pl[i].pa[j].y == tem2) {
										this.p.pl[i].pa[j].current = -1;
										this.kill = 1;
										k = true;
										break;
									}
								}
							}

							if (k) {
								break;
							}
						}
					}
				}
			} else {
				for(i = 0; i < 4; ++i) {
					if (this.p.pl[this.current_player].pa[i].x == x && this.p.pl[this.current_player].pa[i].y == y && this.p.pl[this.current_player].pa[i].current + this.dice <= 56) {
						value = i;
						this.flag = 0;
						break;
					}
				}

				if (value != -1) {
					var10000 = this.p.pl[this.current_player].pa[value];
					var10000.current += this.dice;
					if (this.p.pl[this.current_player].pa[value].current == 56) {
						++this.p.pl[this.current_player].coin;
					}

					k = false;
					hou = this.p.pl[this.current_player].pa[value].current;
					if (hou % 13 != 0 && hou % 13 != 8 && hou < 51) {
						for(i = 0; i < 4; ++i) {
							if (i != this.current_player) {
								for(j = 0; j < 4; ++j) {
									tem1 = this.path.ax[this.current_player][this.p.pl[this.current_player].pa[value].current];
									tem2 = this.path.ay[this.current_player][this.p.pl[this.current_player].pa[value].current];
									if (this.p.pl[i].pa[j].x == tem1 && this.p.pl[i].pa[j].y == tem2) {
										this.p.pl[i].pa[j].current = -1;
										this.kill = 1;
										k = true;
										break;
									}
								}
							}

							if (k) {
								break;
							}
						}
					}
				}
			}

			this.repaint();
		}

	}

	public void actionPerformed(ActionEvent e) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	private void updateGame(Graphics g) {
		if (this.p.pl[this.current_player].coin == 4) {
			g.setColor(Color.WHITE);
			g.fillRect(590, 100, 380, 130);
			if (this.current_player == 0) {
				g.setColor(Color.RED);
			} else if (this.current_player == 1) {
				g.setColor(Color.GREEN);
			} else if (this.current_player == 2) {
				g.setColor(Color.YELLOW);
			} else if (this.current_player == 3) {
				g.setColor(Color.BLUE);
			}

			g.setFont(new Font("serif", 1, 40));
			g.drawString("Player " + (this.current_player + 1) + " wins.", 600, 150);
			g.drawString("Congratulations.", 600, 200);
			this.current_player = 0;
			this.la = new Layout(80, 50);
			this.p = new Build_Player(this.la.height, this.la.width);
			this.dice = 0;
			this.flag = 0;
			this.roll = 0;
			this.kill = 0;
		} else if (this.dice != 0) {
			g.setColor(Color.WHITE);
			g.fillRect(590, 100, 380, 130);
			if (this.current_player == 0) {
				g.setColor(Color.RED);
			} else if (this.current_player == 1) {
				g.setColor(Color.GREEN);
			} else if (this.current_player == 2) {
				g.setColor(Color.YELLOW);
			} else if (this.current_player == 3) {
				g.setColor(Color.BLUE);
			}

			g.setFont(new Font("serif", 1, 40));
			g.drawString("Player " + (this.current_player + 1), 600, 150);
			g.drawString("Number on dice is " + this.dice, 600, 200);
		}

		if (this.flag == 0 && this.dice != 0 && this.dice != 6 && this.kill == 0) {
			this.current_player = (this.current_player + 1) % 4;
		}

		this.kill = 0;
	}
}
