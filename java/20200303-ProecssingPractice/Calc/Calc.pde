int counter = 0;

void setup() {
  size(500, 700);
}

void draw() {
  drawRowsCols();
  fillNumbers();
  fillOperators();
}

void drawRowsCols() {
  for (int i=2; i<7; i++) {
    line(0, i * 100, 500, i * 100);
  }
  for (int i=1; i<4; i++) {
    line(i * 125, 200, i * 125, 700);
  }
}

void fillNumbers() {
  textSize(50);
  text(0, 50, 670);
  for (int i=0; i<3; i++) {
    for (int j=0; j<3; j++) {
      text(j * 3 + i + 1, 50 + i * 125, 570 - j * 100);
    }
  }
}

void fillOperators() {
  textSize(50);
  text("sin", 30, 270);
  text("cos", 155, 270);
  text("sqrt", 270, 270);
  text("AC", 405, 270);
  text("+", 420, 370);
  text("-", 420, 470);
  text("*", 420, 570);
  text("/", 420, 670);
  text(".", 180, 660);
  text("=", 290, 670);
}

void clearScreen() {
  rect(0, 0, 500, 200);
}

void mouseClicked() {
  clearScreen();
  int x = mouseX;
  int y = mouseY;
  
  if (0 <= x && x <= 500 && 200 <= y && y <= 700) {
    counter += 1;
    fill(0, 0, 0);
    textSize(140);
    text(counter, 40, 160);
    fill(255, 255, 255);
  } else {
    counter = 0;
  }  
}
