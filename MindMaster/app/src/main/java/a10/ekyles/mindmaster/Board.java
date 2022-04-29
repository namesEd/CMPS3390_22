package a10.ekyles.mindmaster;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Paint paint = new Paint();
    private final int boardXPos;
    private final List<Peg> pegList = new ArrayList<>();
    private final int pegRadius;
    private final int rowVertSpace;
    private final int rowYOffset;
    private int currentRow = 0;
    private PlayButton playButton;
    private final List<Integer> solutionList = new ArrayList<>();
    private final List<Peg> resultPegs = new ArrayList<>();
    private Boolean win = false;
    private final GameActivity gameActivity;
    private SolutionCover solutionCover;

    private Bitmap background;

    public Board(Point screenSize, Resources resources, GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        background = BitmapFactory.decodeResource(resources, R.drawable.board);

        //background = Bitmap.createScaledBitmap(background, (int)(screenSize.y * 0.66), screenSize.y, true);
        if(screenSize.y * 0.63f > screenSize.x) {
            background = Bitmap.createScaledBitmap(background, screenSize.x, (int)(screenSize.x * 1.63f), true);
        } else {
            background = Bitmap.createScaledBitmap(background, (int)(screenSize.y * 0.63f), screenSize.y , true);

        }

        boardXPos = screenSize.x / 2 - background.getWidth()/2;
        pegRadius = Math.round(background.getHeight() / 35f);
        rowYOffset = Math.round(pegRadius + background.getHeight() / 30f);
        rowVertSpace = Math.round(pegRadius + (background.getHeight() / 14.7f));
        generatePegs();
        Point tmp = new Point();
        tmp.x = Math.round(pegList.get(currentRow * 4 + 3).getPos().x + pegRadius * 1.2f);
        tmp.y = pegList.get(currentRow * 4 + 3).getPos().y;
        playButton = new PlayButton(tmp, Math.round(pegRadius * 0.9f));

        Point tmpCoverPos = new Point();
        tmpCoverPos.x = pegList.get(pegList.size() - 4).getPos().x - pegRadius;
        tmpCoverPos.y = pegList.get(pegList.size() -4).getPos().y - pegRadius;
        solutionCover = new SolutionCover(tmpCoverPos, pegRadius * 11, pegRadius * 2);
    }

    private void generatePegs() {
        for(int r = 0; r<10; r ++) {
            for(int c = 0; c < 4; c++) {
                int pegX = c * pegRadius * 3 + boardXPos + pegRadius * 2;
                int pegY = rowYOffset + r * rowVertSpace;
                Peg tmp = new Peg(0, pegRadius, new Point(pegX, pegY));
                pegList.add(tmp);
                if(c == 3) {
                    int rPegRadius = Math.round(pegRadius * 0.40f);
                    resultPegs.add(new Peg(0, rPegRadius, new Point(tmp.getPos().x + pegRadius * 3, Math.round(tmp.getPos().y - rPegRadius  * 1.2f))));
                    resultPegs.add(new Peg(0, rPegRadius, new Point(tmp.getPos().x + pegRadius * 4, Math.round(tmp.getPos().y - rPegRadius  * 1.2f))));
                    resultPegs.add(new Peg(0, rPegRadius, new Point(tmp.getPos().x + pegRadius * 3, Math.round(tmp.getPos().y + rPegRadius  * 1.2f))));
                    resultPegs.add(new Peg(0, rPegRadius, new Point(tmp.getPos().x + pegRadius * 4, Math.round(tmp.getPos().y + rPegRadius  * 1.2f))));
                }
            }
        }

        //Set Solution
        for(int i = pegList.size()-1; i > pegList.size() -5; i --) {
            int tmp = (int)(Math.random() * 5 + 1);
            solutionList.add(0, tmp);
            pegList.get(i).setColor(tmp);
        }

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(background, boardXPos, 0, paint);
        for(Peg p : pegList) {
            p.draw(canvas);
        }
        for (Peg p : resultPegs) {
            p.draw(canvas);
        }
        playButton.draw(canvas);
        solutionCover.draw(canvas);
        if(solutionCover.offScreen) {
            goToEndGame();
        }
    }

    public void onClick(int x, int y) {
        Point point = new Point(x, y);
        for (int i = currentRow * 4; i < currentRow * 4 + 4; i++) {
            pegList .get(i).isPegClicked(point);
        }

        if(playButton.isPlayButtonClicked(point)) {
            evaluateRow();
        }

    }

    private void evaluateRow() {
        //check our answers against the solution
        List<Integer> rowList = new ArrayList<>();
        for (int i = 0; i < 4; i ++) {
            rowList.add(pegList.get(currentRow * 4 + i).selectedPaint);
        }

        List<Integer> tmpSolution = new ArrayList<>();
        tmpSolution.addAll(solutionList);

        int emptyPeg = 0;
        int exactMatches = 0;
        // Look for exact matches first
        for(int i = 3; i >= 0; i--) {
            if (rowList.get(i)   == tmpSolution.get(i)) {
                exactMatches ++;
                resultPegs.get(currentRow * 4 + emptyPeg).setColor(3);
                emptyPeg ++;
                rowList.remove(i);
                tmpSolution.remove(i);
            }
        }

        //Look for color matches second
        for(int i = rowList.size() -1; i >= 0; i--) {
            for(int j = tmpSolution.size() -1; j >=0; j--) {
                if (rowList.get(i) == tmpSolution.get(j)) {
                    resultPegs.get(currentRow * 4 + emptyPeg).setColor(4);
                    emptyPeg++;
                    rowList.remove(i);
                    tmpSolution.remove(j);
                    break;
                }
            }
        }

        if(exactMatches == 4) {             //Check win condition
            win = true;
            solutionCover.show = true;
        } else if (currentRow == 8) {       // Check lose condition
             solutionCover.show = true;
        } else {                            // advance current row
            advanceCurrentRow();
        }
    }

    private void goToEndGame() {
        Intent intent = new Intent(gameActivity, EndGameActivity.class);
        intent.putExtra("win", win);
        gameActivity.startActivity(intent);
        gameActivity.finish();
    }

    private void advanceCurrentRow() {
        currentRow ++;
        playButton.moveDown(rowVertSpace);
    }
}
