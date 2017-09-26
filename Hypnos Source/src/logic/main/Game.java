package logic.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

import logic.audio.AudioPlayer;
import logic.client.HypnosClient;
import logic.levels.Level1;
import logic.screens.Achievement;
import logic.screens.Controls;
import logic.screens.Credits;
import logic.screens.GameOver;
import logic.screens.Help;
import logic.screens.HighScores;
import logic.screens.LevelSelect;
import logic.screens.Login;
import logic.screens.LoginApp;
import logic.screens.Menu;
import logic.screens.Options;

public class Game extends Canvas implements Runnable{

	//Game & Window Information
	private static final long serialVersionUID = 1L;
	public static final int GAMEWIDTH = 780;
	public static final int GAMEHEIGHT = 585;
	public static final int SCALE = 1;
	private String user;

	private boolean running = false;

	private boolean[] bgMusicPlaying;
	private int bgMusicPlayingIdx;
	private AudioPlayer[] bgMusicArr;
	
	private boolean close;
	
	//Game States
	public enum STATE {
		MENU, 
		OPTIONS,
		LOGIN,
		LEVEL_SELECT,
		GAME_OVER,
		ACHIEVEMENT,
		CONTROLS,
		HIGH_SCORES,
		HELP,
		CREDITS,
		CREATE_USER,
		LEVEL_1,
		LEVEL_2,
		LEVEL_3,
		LEVEL_4,
		LEVEL_5,
		LEVEL_6
		
	}
	
	private STATE curState = STATE.LOGIN;
	private STATE prevState;
	
	//Game Screen Variables
	private Menu menu;
	private Options options;
	private LevelSelect levelSelect;
	private GameOver gameOver;
	private Achievement achievement;
	private Controls controls;
	private HighScores highScores;
	private Help help;
	private Credits credits;
	private Login login; 
	private Level1 level1;
	
	private HypnosClient client;
	
	private static Game instance;
	
	private Game() {
		
	}
	
	public static synchronized Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		
		return instance;
	}
	
	//Initialize Values for the Game
	private void init() throws IOException {
		requestFocus();
    	
    	menu = new Menu(this);
    	options = new Options(this);	
	    levelSelect = new LevelSelect(this);
	    gameOver = new GameOver(this);
	    achievement = new Achievement(this);
	    highScores = new HighScores(this);
	    help = new Help(this);
	    controls = new Controls(this);
	    credits = new Credits(this);
	    login = new Login(this); 
	    
    	this.addMouseListener(new MouseInput(this));
    	this.addKeyListener(new KeyInput(this));
    	
    	bgMusicArr = new AudioPlayer[]{
    		new AudioPlayer("/menu-music.mp3"), //from bensound
    		new AudioPlayer("/level1-music.mp3")
    	};
    	
    	bgMusicPlaying = new boolean[]{
    		true,
    		false
    	};
    	bgMusicPlayingIdx = 0;
    	bgMusicArr[bgMusicPlayingIdx].play();
    	
    	// open login applet
    	LoginApp loginapp = new LoginApp(this);
    	loginapp.show(); 
    	
    	close = false;
	}
	
	//Start The Game
	private void start() throws IOException{
		if(running)
    		return;
    	running = true;
    	play();
	}
	
	//Stop the Game
	private synchronized void stop() {
		if(!running) 
    		return;
    	
    	running = false;

	}
	
	//Constantly Runs the game
	
	public void play() throws IOException{
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if(delta >= 1) {
				delta = 1;
				tick();
				delta--;
			}
			render();
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
			
			if (close) {
				break;
			}
		}
		
		stop();
	}
	
	
	private void tick() {
		if (curState == STATE.LEVEL_1) {
			level1.update();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		adjustMusicVolume(Options.MAXVOL, Options.getMVol(), bgMusicArr, bgMusicPlayingIdx);
		g.clearRect(0, 0, GAMEWIDTH, GAMEHEIGHT);
		
		renderState(g);
		g.dispose();
		bs.show();
	}
	
	private void renderState(Graphics g) {
		if (curState == STATE.MENU) {
			menu.render(g);
		} 
		else if (curState == STATE.OPTIONS) {
			options.render(g);
		} 
		else if (curState == STATE.LEVEL_SELECT) {
			levelSelect.render(g);
		}
		else if (curState == STATE.GAME_OVER) {
			switchbgMusic(0);
			gameOver.render(g);
		}
		else if (curState == STATE.ACHIEVEMENT) {
			achievement.render(g);
		}
		else if (curState == STATE.CONTROLS) {
			controls.render(g);
		}
		else {
			renderOtherStates(g);
		}
	}
	
	private void renderOtherStates(Graphics g) {
		if (curState == STATE.HIGH_SCORES) {
			highScores.render(g);
		}
		else if (curState == STATE.HELP) {
			help.render(g);
		}
		else if (curState == STATE.CREDITS) {
			credits.render(g);
		}
		else if (curState == STATE.LEVEL_1) {
			switchbgMusic(1);
			level1.render(g);
		}
		else if (curState == STATE.LOGIN) {
			login.render(g); 
		}
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if (curState == STATE.LEVEL_1) {
			level1.keyPressed(key);
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if (curState == STATE.LEVEL_1) {
			level1.keyReleased(key);
		}
	}
	
	public void switchbgMusic(int newIdx) {
		if (bgMusicPlayingIdx != newIdx) {
			if (bgMusicPlaying[bgMusicPlayingIdx]) {
				bgMusicPlaying[bgMusicPlayingIdx] = false;
				bgMusicPlaying[newIdx] = true;
			}
			bgMusicArr[bgMusicPlayingIdx].stop();
			bgMusicArr[newIdx].play();
			bgMusicPlayingIdx = newIdx;
		}
	}
	
	public void adjustMusicVolume(int maxVol, int currentVol, AudioPlayer[] musicArr, int playingIdx) {
		musicArr[playingIdx].setVolume(maxVol, currentVol);
	}
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public LevelSelect getLevelSelect() {
		return levelSelect;
	}

	public void setLevelSelect(LevelSelect levelSelect) {
		this.levelSelect = levelSelect;
	}

	public GameOver getGameOver() {
		return gameOver;
	}

	public void setGameOver(GameOver gameOver) {
		this.gameOver = gameOver;
	}

	public Achievement getAchievement() {
		return achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

	public Controls getControls() {
		return controls;
	}

	public void setControls(Controls controls) {
		this.controls = controls;
	}

	public HighScores getHighScores() {
		return highScores;
	}

	public void setHighScores(HighScores highScores) {
		this.highScores = highScores;
	}

	public Help getHelp() {
		return help;
	}

	public void setHelp(Help help) {
		this.help = help;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Level1 getLevel1() {
		return level1;
	}

	public void setLevel1(Level1 level1) {
		this.level1 = level1;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
	public STATE getCurState() {
		return curState;
	}

	public void setCurState(STATE curState) {
		this.curState = curState;
	}

	public STATE getPrevState() {
		return prevState;
	}

	public void setPrevState(STATE prevState) {
		this.prevState = prevState;
	}

	public void setClose(boolean close) {
		this.close = close;
	}
	
	public HypnosClient getClient() {
		return client;
	}
	@Override
	public void run() {
		// Auto-generated method stub
		
	}
	
	public static void main (String[] args) throws IOException{
		
		Game.getInstance().setPreferredSize(new Dimension(GAMEWIDTH * SCALE, GAMEHEIGHT * SCALE));
		Game.getInstance().setMaximumSize(new Dimension(GAMEWIDTH * SCALE, GAMEHEIGHT * SCALE));
		Game.getInstance().setMinimumSize(new Dimension(GAMEWIDTH * SCALE, GAMEHEIGHT * SCALE));
	
		JFrame frame = new JFrame("Hypnos");
		frame.add(Game.getInstance());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		Game.getInstance().start();
		System.exit(0);
	}


}
