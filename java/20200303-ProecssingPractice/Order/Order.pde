int counter = 0;

int scrollX = 0; // x position of scroll text

// control circle 
int rad1 = 0;
int rad2 = 0;
int rad3 = 0;

String type = ""; // type of pizza
String size = ""; // size of pizza
float price = 0; //price of pizza 

int page = 1; // page number to switch

color brown = color(80, 50, 10); // brown
color red   = color(255, 0, 0); // red;
color green = color(0, 255, 0); // green

int rectWidth = 0; // use in transition page

void setup() {
  size(700, 700);
}

void draw() {
  if (page == 1) {
    // diaw pizzas, click to enter page2
    strokeWeight(0);
    rect(0, 0, 700, 700);
    drawCircles();
    drawScrollText();
    drawCuts1();
    drawCuts2();
    drawCuts3();
    drawPrices(); 
    updateRad();
  } else if (page == 2) {
    // draw transition page
    drawTransitionPage();
  } else if (page == 3) {
    // show kinds, click to enter last page to show result
    drawKinds();
  } else if (page == 4) {
    // show results
    drawResult();
  } 
}

void drawScrollText() {
  scrollX += 3;
  scrollX = scrollX % 700;
  fill(0, 0, 0);
  textSize(40);
  text("Pick the size first...", scrollX, 100);
  fill(255, 255, 255);
}

void drawCircles() {
  stroke(0, 0, 0);
  strokeWeight(10);
  int rad = 0;
  int[] xPos = {100, 575, 325};
  for (int i=0; i < 3; i++) {
    rad = (i+2) * 50;
    ellipse(xPos[i], 350, rad, rad);
  }
}

void drawCuts1() {
  stroke(#9B6262);
  strokeWeight(8);
  float dx = 0;
  float dy = 0;
  int rad = rad1;
  for (int i=0; i<4; i++) {
    dx = 42 * cos (radians(rad));
    dy = 42 * sin (radians(rad));
    line(100-dx, 350-dy, 100+dx, 350+dy);
    rad += 45;
  }
}

void drawCuts2() {
  stroke(#9B6262);
  strokeWeight(8);
  float dx = 0;
  float dy = 0;
  int rad = rad2;
  for (int i=0; i<4; i++) {
    dx = 92 * cos (radians(rad));
    dy = 92 * sin (radians(rad));
    line(325-dx, 350-dy, 325+dx, 350+dy);
    rad += 45;
  }
}

void drawCuts3() {
  stroke(#9B6262);
  strokeWeight(8);
  float dx = 0;
  float dy = 0;
  int rad = rad3;
  for (int i=0; i<4; i++) {
    dx = 67 * cos (radians(rad));
    dy = 67 * sin (radians(rad));
    line(575-dx, 350-dy, 575+dx, 350+dy);
    rad += 45;
  }
}

void drawPrices() {
  fill(0, 0, 0);
  textSize(40);
  text("$8.0", 60, 550);
  text("$16.8", 270, 550);
  text("$11.2", 520, 550);
  fill(255, 255, 255);
}

boolean mouseInCircle(int posX, int posY, int x, int y, int rad) {
  float dis = sqrt((posX - x) * (posX - x) + (posY - y) * (posY - y));
  return dis < rad;
}

void updateRad() {
  if (mouseInCircle(mouseX, mouseY, 100, 350, 50)) {
    rad1 += 2;
  } else {
    rad1 = 0;
  }
  
  if (mouseInCircle(mouseX, mouseY, 325, 350, 100)) {
    rad2 += 2;
  } else {
    rad2 = 0;
  }
  
  if (mouseInCircle(mouseX, mouseY, 575, 350, 75)) {
    rad3 += 2;
  } else {
    rad3 = 0;
  }
}

void mouseClicked() {
  if (page == 1) {
    if (mouseInCircle(mouseX, mouseY, 100, 350, 50)) {
      price = 8.0;
      size = "medium";
      page = 2;
    } else if (mouseInCircle(mouseX, mouseY, 325, 350, 50)) {
      price = 16.8;
      size = "extra large";
      page = 2;
    } else if (mouseInCircle(mouseX, mouseY, 575, 350, 50)) {
      price = 11.2;
      size = "large";
      page = 2;
    }
  }
  
  if (page == 3) {
    if (mouseX < 233) {
      type = "Vegeterian";
      page = 4;
    } else if (mouseX > 466) {
      type = "BBQ Chicken";
      page = 4;
    } else {
      type = "Extravaganza";
      page = 4;
    }
  }
}

void drawTransitionPage() {
  
  if (rectWidth <= 117) {
    strokeWeight(0);
    rect(117-rectWidth, 0, rectWidth*2, 700);
    fill(brown);
    rect(350-rectWidth, 0, rectWidth*2, 700);
    fill(red);
    rect(583-rectWidth, 0, rectWidth*2, 700);
    fill(green);
    rectWidth += 3;
  } else {
    page = 3;
  }
}

void drawKinds() {
  strokeWeight(0);
  textSize(30);
  rect(0, 0, 233, 700);
  fill(0, 0, 0);
  text("V\ne\ng\ne\nt\ne\nr\ni\na\nn", 100, 50);
  fill(brown);
  rect(233, 0, 466, 700);
  fill(0, 0, 0);
  text("E\nx\nt\nr\na\nv\na\ng\na\nn\nz\na", 333, 50);
  fill(red);
  rect(466, 0, 700, 700);
  fill(0, 0, 0);
  text("B\nB\nQ\n\nC\nh\ni\nc\nk\ne\nn", 566, 50);
  fill(green);
}

void drawResult() {
  strokeWeight(0);
  rect(0, 0, 700, 700);
  fill(0, 0, 0);
  textSize(40);
  text("The summary of your order is:", 0, 50);
  text("Pizza kind: " + type, 0, 100);
  text("Pizza Size: " + size, 0, 150);
  text("The total is: $" + price, 0, 200);
  fill(255, 255, 255);
}
