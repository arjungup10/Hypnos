package logic.entity.enemies;

import logic.entity.Enemy;
import logic.levels.TileMap;

public class EnemyFactory {
	public Enemy getEnemy(String enemyType, TileMap tm) {
		if (enemyType == null) {
			return null;
		}
		else if ("spider".equals(enemyType)) {
			return new Spider(tm);
		}
		else if ("shark".equals(enemyType)) {
			//create shark here
		}
		else if ("hawk".equals(enemyType)) {
			//create hawk here
		}
		return null;
	}
}
