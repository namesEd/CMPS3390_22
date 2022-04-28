package a10.ekyles.mindmaster;

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

    private Bitmap background;

    public Board(Point screenSize, Resources resources) {
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
    }

    private void generatePegs() {
        for(int r = 0; r<10; r ++) {
            for(int c = 0; c < 4; c++) {
                int pegX = c * pegRadius * 3 + boardXPos + pegRadius * 2;
                int pegY = rowYOffset + r * rowVertSpace;
                Peg tmp = new Peg(0, pegRadius, new Point(pegX, pegY));
                pegList.add(tmp);
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
            playButton.draw(canvas);
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
        // advance current row
         advanceCurrentRow();
    }

    private void advanceCurrentRow() {
        currentRow ++;
        playButton.moveDown(rowVertSpace);
    }
}
