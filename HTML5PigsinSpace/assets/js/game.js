function start(){
	$("#logo").hide();
	startGame();
}

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
			//console.log("drawSpaceShip");
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
			//console.log("drew the wolf");
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

	var highlightPig = 0;
	var highlightEnemy = 0;
	$('body').keyup(function(e){
		switch(e.keyCode){
			case 49:
				highlightPig = 1;
				break;
			case 50:
				highlightPig = 2;
				break;
			case 51:
				highlightPig = 3;
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
			var img = new Image();
			img.src = "assets/images/pig1.png";
			var x = 15;
			var	y = height - 170;
			var nextTurn = 0;
			var canAttack = function(){return nextTurn <= turnNumber; };
			var turnCooldown = 1;
			var damage = 100;
			var attack = function(){
				if (canAttack()){
					nextTurn = turnNumber + turnCooldown;
					return Math.round(damage * Math.random());
				}
				return 0;
			};
			var getNextTurn = function(){return nextTurn;};
			return {
				nextTurn: getNextTurn,
				name: "Pig",
				attackName: "Attack",
				img: img,
				turnCooldown: turnCooldown,
				damage: damage,
				attack: attack,
				canAttack: canAttack,
				getX: function(){return x;},
				getY: function(){return y;},
				setX: function(_x){x = _x;},
				setY: function(_y){y = _y;},
				getImg: function(){return img;}
			};
		};

		var pig1 = new pig();
		pig1.name = "Dr. Julius Strangepork";
		pig1.attackName = "Laser";
		pig1.turnCooldown = 1;
		pig1.damage = 100;
		pig1.img.src = "assets/images/pig1.png";

		var pig2 = new pig();
		pig2.name = "Captain Link Hogthrob";
		pig2.attackName = "Rocket Launch";
		pig2.turnCooldown = 5;
		pig2.damage = 800;
		pig2.img.src = "assets/images/pig2.png";
		pig2.setX(165);
		pig2.setY(height-150-22);
		console.log(pig2);

		var pig3 = new pig();
		pig3.name = "First Mate Piggy";
		pig3.attackName = "Piggy Slap";
		pig3.turnCooldown = 3;
		pig3.damage = 300;
		pig3.img.src = "assets/images/pig3.png";
		pig3.setX(315);

		var drawPigs = function(){
			for (var pigCounter = 1; pigCounter <= 3; pigCounter++) {
				var pig = getPig(pigCounter);
				ctx.font = "18px sans-serif";
				ctx.fillStyle = "white";
				ctx.drawImage(pig.getImg(), pig.getX(), pig.getY());
				ctx.fillText(pigCounter, pig.getX() + 40, pig.getY() - 10);
				if (highlightPig == pigCounter && pig.canAttack()){
					ctx.drawImage(arrowImg, pig.getX() + 100, pig.getY() - 25, 50, 50);
				}

				if (pig.canAttack()) {
					ctx.fillText(pig.attackName, pig.getX(), pig.getY() + 160);
				} else {
					ctx.fillText("Next move in " + (pig.nextTurn() - turnNumber) + " turns", pig.getX(), pig.getY() + 160);
				}
			}
		};

		var getPig = function(pigNo){
			switch(pigNo){
				case 1: return pig1;
				case 2: return pig2;
				case 3: return pig3;
			}
		};

		var canAnyAttack = function(){
			return pig1.canAttack() || pig2.canAttack() || pig3.canAttack();
		};

		return { drawPigs: drawPigs, getPig: getPig, canAnyAttack: canAnyAttack };
	}());

	var gameoverImg = new Image();
	gameoverImg.src = "assets/images/gameover.png";

	var doAttacks = function(){
		if ( highlightPig && highlightEnemy) {
			var enemy = enemies.getEnemy(highlightEnemy);
			var pig = pigs.getPig(highlightPig);
			console.log(pig);
			if (enemy.isAlive() && pig.canAttack()) {
				var dmg = pigs.getPig(highlightPig).attack();
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