var start = function(){
	$("#logo").hide();
	startGame();
};

$('body').keyup(function(e){
	if(e.keyCode == 32){
		start();
	}
}).click(start);

var startGame = function(){
	$("body").unbind().css("background-image", "url('assets/images/spacetile.png')").keyup(drawScreen);
	$("#c").show();

	var width = 900;
	var height = 500;
	var turnNumber = 1;

	var c = document.getElementById('c');
	c.width = width;
	c.height = height;

	var ctx = c.getContext('2d');

	var clear = function(){
		ctx.clearRect(0, 0, width, height);
	};

	var playSong = function(){
		document.getElementById("themeSong").play();
	};

	var spaceShip = (function(){
		var x = 0;
		var y = 0;
		var health = 10000;
		var spaceship = new Image();
		spaceship.src = "assets/images/spaceship1.png";

		var enterSpaceShip = function(){
			x = 500;
			y = height;
			var endingY = height - 200;


			var moveShip = function(){
				y -= 5;
				if ( y > endingY )
				{
					clear();
					drawSpaceShip();
					setTimeout(moveShip, 50);
				}
				else {
					executeNext();
				}
			};
			playSong();
			moveShip();
		};

		var drawSpaceShip = function(){
			ctx.drawImage(spaceship, x, y, 114, 200);
		};

		var takeDamage = function(dmg){
			health -= dmg;
			eventLog.write("The spaceship took " + dmg + " damage.");
			if (health <= 0){
				eventLog.write("Spaceship is dead");
			}
		};

		var stillAlive = function(){
			return health > 0;
		};

		return { drawSpaceShip: drawSpaceShip, enterSpaceShip: enterSpaceShip, takeDamage: takeDamage, stillAlive: stillAlive};
	}());

	var introText = (function(){
		var x = 0;
		var y = 0;

		var wolf = new Image();
		wolf.src = "assets/images/bbw.png";

		var drawWolf = function(){
			ctx.drawImage(wolf, x, y, 250, 256);
		};

		var wolfSpeech = function(){
			var speech = [
				"Hello Little Piggies"
			];
			var lineNum = 0;

			var writeText = function(){
				ctx.fillText(speech[lineNum], 20, 20);
				setTimeout(writeText, 50);
			};
		};

		var start = function(){
			x = width - wolf.width;
			drawWolf();

			x = width + 1;
			y = height + 1;

			executeNext();
		};

		return { drawWolf: drawWolf, start: start };
	}());

	var arrowImg = new Image();
	arrowImg.src = "assets/images/arrow.png";

	var highlightPig = null;
	var highlightEnemy = null;
	$('body').keyup(function(e){
		switch(e.keyCode){
			case 49:
				highlightPig = 0;
				break;
			case 50:
				highlightPig = 1;
				break;
			case 51:
				highlightPig = 2;
				break;
			case 65:
				highlightEnemy = 1;
				break;
			case 66:
				highlightEnemy = 2;
				break;
			case 67:
				highlightEnemy = 3;
				break;
		}
		drawScreen();
	});

	var resetHighlights = function(){
		highlightPig = 0;
		highlightEnemy = 0;
		drawScreen();
	};

	var enemies = (function(){
		var enemy = function(){
			var x = 0;
			var y = 0;
			var alive = false;
			var health = 0;
			var name = "Enemy";
			var damage = 100;

			var kill = function(){
				eventLog.write(getName() + " has died");
				alive = false;
				x = 0;
				y = 0;
			};

			var create = function(xPos, yPos, _health, _name, _damage){
				alive = true;
				x = xPos;
				y = yPos;
				health = _health;
				name = _name;
				damage = _damage;
			};

			var attack = function(){
				return Math.round(100 * Math.random());
			};

			var isAlive = function(){
				return alive;
			};

			var getX = function(){return x;};
			var getY = function(){return y;};

			var getName = function(){return name;};

			var takeDamage = function(dmg){
				health -= dmg;
				eventLog.write(getName() + " took " + dmg + " damage.");
				if (health <= 0){
					kill();
				}
			};

			return { create: create, kill: kill, attack: attack, isAlive: isAlive, getX: getX, getY: getY, takeDamage: takeDamage};
		};

		var enemy1 = new enemy();
		enemy1.create(10, 10, 5000, "Enemy1", 100);
		var enemy2 = new enemy();
		enemy2.create(200, 200, 3000, "Enemy2", 200);
		var enemy3 = new enemy();
		enemy3.create(400, 100, 500, "Enemy3", 500);

		var enemyImg = new Image();
		enemyImg.src = "assets/images/enemyspaceship.png";

		var drawEnemies = function(){
			if (enemy1.isAlive()){
				ctx.font = "bold 24px sans-serif";
				ctx.drawImage(enemyImg, enemy1.getX(), enemy1.getY(), 100, 43);
				ctx.fillText("A", enemy1.getX() + 40, enemy1.getY() + 21);
				if (highlightEnemy == 1){
					ctx.drawImage(arrowImg, enemy1.getX() + 100, enemy1.getY() - 25, 50, 50);
				}
			}

			if (enemy2.isAlive()){
				ctx.drawImage(enemyImg, enemy2.getX(), enemy2.getY(), 100, 43);
				ctx.fillText("B", enemy2.getX() + 40, enemy2.getY() + 21);
				if (highlightEnemy == 2){
					ctx.drawImage(arrowImg, enemy2.getX() + 100, enemy2.getY() - 25, 50, 50);
				}
			}

			if (enemy3.isAlive()){
				ctx.drawImage(enemyImg, enemy3.getX(), enemy3.getY(), 100, 43);
				ctx.fillText("C", enemy3.getX() + 40, enemy3.getY() + 21);
				if (highlightEnemy == 3){
					ctx.drawImage(arrowImg, enemy3.getX() + 100, enemy3.getY() - 25, 50, 50);
				}
			}
		};

		var getEnemy = function(enemyNo){
			switch(enemyNo){
				case 1: return enemy1;
				case 2: return enemy2;
				case 3: return enemy3;
			}
			return new enemy();
		};

		var stillAlive = function(){
			return enemy1.isAlive() || enemy2.isAlive() || enemy3.isAlive();
		};

		return { drawEnemies: drawEnemies, getEnemy: getEnemy, stillAlive: stillAlive };
	}());

	var pigs = (function(){
		var pig = function(){
			var config = {
				x: 15,
				y: height - 170,
				nextTurn: 0,
				turnCooldown: 1,
				damage: 100,
				pigName: "Pig",
				attackName: "Attack"
			};
			var img = new Image();
			img.src = "assets/images/pig1.png";

			var canAttack = function(){return config.nextTurn <= turnNumber;};

			var calculateDamage = function(){
				if (canAttack()){
					config.nextTurn = turnNumber + config.turnCooldown;
					return Math.round(config.damage * Math.random());
				}
				return 0;
			};

			var configure = function(options) {
				$.extend(config, options);

				// img is an object, so we have to handle it separately
				if (options.hasOwnProperty("src")) {
					img.src = options.src;
				}
			};

			return {
				configure: configure,
				calculateDamage: calculateDamage,
				canAttack: canAttack,
				getNextTurn: function(){return config.nextTurn;},
				getName: function(){return config.pigName;},
				getAttackName: function(){return config.attackName;},
				getTurnCooldown: function(){return config.turnCooldown;},
				getX: function(){return config.x;},
				getY: function(){return config.y;},
				getImg: function(){return img;}
			};
		};

		var pigConfigs = [
			{
				name: "Dr. Julius Strangepork",
				attackName: "Laser",
				turnCooldown: 1,
				damage: 100,
				src: "assets/images/pig1.png"
			},
			{
				name: "Captain Link Hogthrob",
				attackName: "Rocket Launch",
				turnCooldown: 5,
				damage: 800,
				src: "assets/images/pig2.png",
				x: 165,
				y: height-150-22
			},
			{
				name: "First Mate Piggy",
				attackName: "Piggy Slap",
				turnCooldown: 3,
				damage: 300,
				src: "assets/images/pig3.png",
				x: 315
			}
		];

		var pigArray = [];
		var createPigs = (function(){
			$.each(pigConfigs, function(idx, config){
				var _pig = new pig();
				_pig.configure(config);
				pigArray.push(_pig);
			});
		})();

		var drawPigs = function(){
			$.each(pigArray, function(pigCounter, pig){
				ctx.font = "18px sans-serif";
				ctx.fillStyle = "white";
				ctx.drawImage(pig.getImg(), pig.getX(), pig.getY());
				ctx.fillText(pigCounter + 1, pig.getX() + 40, pig.getY() - 10);
				if (highlightPig == pigCounter && pig.canAttack()){
					ctx.drawImage(arrowImg, pig.getX() + 100, pig.getY() - 25, 50, 50);
				}

				if (pig.canAttack()) {
					ctx.fillText(pig.getAttackName(), pig.getX(), pig.getY() + 160);
				} else {
					ctx.fillText("Next move in " + (pig.getNextTurn() - turnNumber) + " turns", pig.getX(), pig.getY() + 160);
				}
			});
		};

		var getPig = function(pigNo){
			return pigArray[pigNo];
		};

		var canAnyAttack = function(){
			var canAttack = false;
			$.each(pigArray, function(idx, pig){
				if (pig.canAttack()) {
					canAttack = true;
					return false; // stop iterating
				}
			});
			return canAttack;
		};

		return { drawPigs: drawPigs, getPig: getPig, canAnyAttack: canAnyAttack };
	}());

	var gameoverImg = new Image();
	gameoverImg.src = "assets/images/gameover.png";

	var doAttacks = function(){
		if (highlightPig >= 0 && highlightEnemy) {
			var enemy = enemies.getEnemy(highlightEnemy);
			var pig = pigs.getPig(highlightPig);
			if (enemy.isAlive() && pig.canAttack()) {
				var dmg = pigs.getPig(highlightPig).calculateDamage();
				enemy.takeDamage(dmg);
				resetHighlights();
				if (enemy.isAlive()) {
					var enemyDmg = enemy.attack();
					spaceShip.takeDamage(enemyDmg);
				}

				turnNumber++;

				while(!pigs.canAnyAttack()){
					eventLog.write("No pigs can attack, skipping this turn.");
					turnNumber++;
				}

				if (!spaceShip.stillAlive()){
					clear();
					ctx.drawImage(gameoverImg, 200,150);
					eventLog.write("Game Over, you lose.");
				}
				if (!enemies.stillAlive()){
					eventLog.write("Game Over, you win.");
				}
			}
		}
	};

	var drawScreen = function(){
		clear();
		spaceShip.drawSpaceShip();
		introText.drawWolf();
		enemies.drawEnemies();
		pigs.drawPigs();
		doAttacks();
	};

	var callbackOrder = [spaceShip.enterSpaceShip, introText.start, drawScreen];
	//var callbackOrder = [ drawScreen];

	var callback = -1;

	var executeNext = function(){
		callback++;
		var f = callbackOrder[callback];
		f();
	};

	var eventLog = (function(){
		var e = $("#eventLog");

		var write = function(txt){
			e.prepend("Turn " + turnNumber + ": " + txt + "\n");
		};

		return { write: write };
	}());

	drawScreen();
	executeNext();
};