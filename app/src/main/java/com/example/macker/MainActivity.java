package com.example.macker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.divyanshu.colorseekbar.ColorSeekBar;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int IMAGE_REQUEST = 1;
    SpacingClass spacingClass;
    EasyAccess easyAccess;
    Point p;
    Bitmap bitmap;
    String[] filterColorCode, filterColorName;
    Uri imageUri;
    ImageFilter imageFilter;
    LinearLayout canvasBackground, backgroundList;
    int bold = 0, italic = 0, boldItalic = 0;

    GradientBackgroundClass gradientBackgroundClass = new GradientBackgroundClass();
    SingleColorClass singleColorClass = new SingleColorClass();
    //gradient color
    ImageView addImage1, addImage2, addImage3;
    RelativeLayout multiColorText, galleryOpen;
    GridView GradientGridView, colorGridView;
    LinearLayout mainGradientCreate, mainColorText, addColor1, addColor2, addColor3, gradientPreviewId, randomGradientId;
    int addColorId = 1;
    CheckBox colorPositionChange, gradientAutoApply;
    SeekBar solidOpacitySeekBar, gradientOpacity;
    Spinner gradientSpinner;
    Button applyButton,watermarkSave;
    String[] orientationList = {"Bottom-Top", "Left-Right", "Top-Right", "Top-Left"};
    int orientation = 0;
    GradientDrawable gradientBackground, gradientPreview, constanceGradient, randomGradient;
    int[] colorList = {0, 0, 0}, randomColorList = {0, 0, 0}, gradientColorList = {0, 0, 0};
    RelativeLayout colorBackground, colorText;
    ImageView waterImage1,waterImage2,waterImage3,resultImage1,resultImage2,resultImage3;
    TextWatcher textWatcher = null;
    //main canvas
    TextView mainTextId, mainSubText, firstComma, secondComma, textBold, textItalic, textBoldItalic, letterSpaceMinus,
            letterSpaceCount, letterSpacePlus, lineSpaceMinus, lineSpaceCount, lineSpacePlus, backgroundColorBar, textColorBar,
            watermarkText;
    RelativeLayout saveScreen, reSizeCanvas,contentLayout;
    //control section
    LinearLayout mainControl,watermarkGroup;
    ColorSeekBar textColorSeekBar, backgroundColorSeekBar, shadowColor, customizeColor,watermarkColor;
    SeekBar fontSizeSeekBar, shadowLeftRight, shadowUpDown, shadowBlur, filterSeekBar, customizeWidth, customizeHeight, customizeOpacity, imageBlurSeekBar,watermarkOpacity;
    LinearLayout editLayout, textSizeClose, colorClose, propertyClose, shadowClose, fontClose, filterClose, effectClose,watermarkClose;
    ImageView textSizeCloseIcon, colorCloseIcon, propertyCloseIcon, shadowCloseIcon, fontCloseIcon, filterCloseIcon, effectCloseIcon,
            createBack, createShare, createWallpaper, createDownload, backgroundBack, canvasBackgroundImage, fakeImageView, flip1, flip2,
            watermarkCloseIcon;
    EditText messageEdit, whoSayEdit,watermarkEdit;
    SwitchMaterial commaSwitch, whoSaySwitch, shadowSwitch, textShadowSwitch,watermarkSwitch;
    // control View
    GridView colorFilterGridView, pictureListGridView,watermarkFontGridView;
    CardView pictureListId, textEditId, textSizeId, colorId, propertyId, fontListId, shadowId, filterId, effectId, textSizeLayout, colorLayout, propertyLayout, shadowLayout, fontLayout, filterLayout, effectLayout;
    int blur = 4, leftRight = 5, upDown = 5, color = Color.BLACK, position = 0, randomNum, randomPosition, letterSpace = 0, lineSpace = 0;
    float xDown = 0, yDown = 0, letterSpacingNumber = .02f, lineSpacingNumber = 1f;
    String[] whatSay = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] whoSay = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    //array
    int[] backgroundImage = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4, R.drawable.bg5, R.drawable.bg6,
            R.drawable.bg7, R.drawable.bg8, R.drawable.bg9, R.drawable.bg10, R.drawable.bg11, R.drawable.bg12,
            R.drawable.bg13, R.drawable.bg14, R.drawable.bg15, R.drawable.bg16, R.drawable.bg17, R.drawable.bg18,
            R.drawable.bg19};
    int[] fontArray = {R.font.font1, R.font.font2, R.font.font3, R.font.font4, R.font.font5, R.font.font6, R.font.font7,
            R.font.font8, R.font.font9, R.font.font10, R.font.font11, R.font.font12, R.font.font13, R.font.font14,
            R.font.font15, R.font.font16, R.font.font17, R.font.font18, R.font.font19, R.font.font20, R.font.font21,
            R.font.font22, R.font.font23, R.font.font24, R.font.font25, R.font.font26, R.font.font27, R.font.font28,
            R.font.font29, R.font.font30, R.font.font31, R.font.font32, R.font.font33, R.font.font34, R.font.font35,
            R.font.font36, R.font.font37, R.font.font38, R.font.font39, R.font.font40, R.font.font41, R.font.font42
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        imageFilter = new ImageFilter(); //filter obj
        easyAccess = new EasyAccess();
        spacingClass = new SpacingClass();

        whiteStatus();//status white

        filterColorCode = getResources().getStringArray(R.array.filterColorCode); //get filter color
        filterColorName = getResources().getStringArray(R.array.filterColorName); //get filter name
        randomNum = new Random().nextInt(whoSay.length - 1);//random number
        backgroundList = findViewById(R.id.backgroundList);//background
        editLayout = findViewById(R.id.editLayout);//edit

        //main canvas
        shadowSwitch = findViewById(R.id.shadowSwitch);//for perfect work shadow switch
        createBack = findViewById(R.id.createBack);
        createShare = findViewById(R.id.createShare);
        createWallpaper = findViewById(R.id.createWallpaper);
        createDownload = findViewById(R.id.createDownload);

        saveScreen = findViewById(R.id.saveScreen);
        canvasBackgroundImage = findViewById(R.id.canvasBackgroundImage);
        fakeImageView = findViewById(R.id.fakeImageView);
        canvasBackground = findViewById(R.id.canvasBackground);
        reSizeCanvas = findViewById(R.id.reSizeCanvas);
        contentLayout = findViewById(R.id.contentLayout);
        mainTextId = findViewById(R.id.mainTextId);
        mainSubText = findViewById(R.id.mainSubText);
        firstComma = findViewById(R.id.firstComma);
        secondComma = findViewById(R.id.secondComma);

        //watermark find
        resultImage1 = findViewById(R.id.resultImage1);
        resultImage2 = findViewById(R.id.resultImage2);
        resultImage3 = findViewById(R.id.resultImage3);
        watermarkText = findViewById(R.id.watermarkText);

        //control find
        mainControl = findViewById(R.id.mainControl);
        pictureListId = findViewById(R.id.pictureListId);
        textEditId = findViewById(R.id.textEditId);
        textSizeId = findViewById(R.id.textSizeId);
        colorId = findViewById(R.id.colorId);
        propertyId = findViewById(R.id.propertyId);
        fontListId = findViewById(R.id.fontListId);
        shadowId = findViewById(R.id.shadowId);
        filterId = findViewById(R.id.filterId);
        effectId = findViewById(R.id.effectId);
        //root
        // control view
        textSizeLayout = findViewById(R.id.textSizeLayout);
        colorLayout = findViewById(R.id.colorLayout);
        propertyLayout = findViewById(R.id.propertyLayout);
        shadowLayout = findViewById(R.id.shadowLayout);
        fontLayout = findViewById(R.id.fontLayout);
        filterLayout = findViewById(R.id.filterLayout);
        effectLayout = findViewById(R.id.effectLayout);
        //control listener
        createBack.setOnClickListener(this);
        createShare.setOnClickListener(this);
        createWallpaper.setOnClickListener(this);
        createDownload.setOnClickListener(this);

        pictureListId.setOnClickListener(this);
        textEditId.setOnClickListener(this);
        textSizeId.setOnClickListener(this);
        colorId.setOnClickListener(this);
        propertyId.setOnClickListener(this);
        fontListId.setOnClickListener(this);
        shadowId.setOnClickListener(this);
        filterId.setOnClickListener(this);
        effectId.setOnClickListener(this);

        //random load background image

        easyAccess.singleView(this, "");
        easyAccess.getCurrentBackground(easyAccess.setCurrentBackground() + 1);

        if (easyAccess.setCurrentBackground() < backgroundImage.length) {
            canvasBackgroundImage.setImageResource(backgroundImage[easyAccess.setCurrentBackground()]);
            canvasBackground.getBackground().setAlpha(100);
        } else {
            easyAccess.getCurrentBackground(0);
        }
        //random load font
        easyAccess.getCurrentFont(easyAccess.setCurrentFont() + 1);
        if (easyAccess.setCurrentFont() < fontArray.length) {
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), fontArray[easyAccess.setCurrentFont()]);
            mainTextId.setTypeface(typeface);
            mainSubText.setTypeface(typeface);
        } else {
            easyAccess.getCurrentFont(0);
        }

        //control view listener
        //mainTextId.getPaint().setStrokeWidth(2);
        //mainTextId.getPaint().setStyle(Paint.Style.STROKE);
        contentLayout.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {

                    if (backgroundList.getVisibility() != View.VISIBLE && editLayout.getVisibility() != View.VISIBLE && textSizeLayout.getVisibility() != View.VISIBLE && colorLayout.getVisibility() != View.VISIBLE && fontLayout.getVisibility() != View.VISIBLE &&
                            effectLayout.getVisibility() != View.VISIBLE && filterLayout.getVisibility() != View.VISIBLE && propertyLayout.getVisibility() != View.VISIBLE && shadowLayout.getVisibility() != View.VISIBLE) {
                        opnEditText();
                    }

                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (backgroundList.getVisibility() != View.VISIBLE && editLayout.getVisibility() != View.VISIBLE && textSizeLayout.getVisibility() != View.VISIBLE && colorLayout.getVisibility() != View.VISIBLE && fontLayout.getVisibility() != View.VISIBLE &&
                        effectLayout.getVisibility() != View.VISIBLE && filterLayout.getVisibility() != View.VISIBLE && propertyLayout.getVisibility() != View.VISIBLE && shadowLayout.getVisibility() != View.VISIBLE) {
                    switch (event.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            xDown = event.getX();
                            yDown = event.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float moveX, moveY;
                            moveX = event.getX();
                            moveY = event.getY();

                            float distanceX = moveX - xDown;
                            float distanceY = moveY - yDown;
                            reSizeCanvas.setX(reSizeCanvas.getX() + distanceX);
                            reSizeCanvas.setY(reSizeCanvas.getY() + distanceY);
                            break;
                    }
                }

                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        reSizeCanvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (backgroundList.getVisibility() != View.VISIBLE && editLayout.getVisibility() != View.VISIBLE && textSizeLayout.getVisibility() != View.VISIBLE && colorLayout.getVisibility() != View.VISIBLE && fontLayout.getVisibility() != View.VISIBLE &&
                        effectLayout.getVisibility() != View.VISIBLE && filterLayout.getVisibility() != View.VISIBLE && propertyLayout.getVisibility() != View.VISIBLE && shadowLayout.getVisibility() != View.VISIBLE) {
                    switch (event.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            xDown = event.getX();
                            yDown = event.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float moveX, moveY;
                            moveX = event.getX();
                            moveY = event.getY();

                            float distanceX = moveX - xDown;
                            float distanceY = moveY - yDown;
                            reSizeCanvas.setX(reSizeCanvas.getX() + distanceX);
                            reSizeCanvas.setY(reSizeCanvas.getY() + distanceY);
                            break;
                    }
                }

                return true;
            }
        });




        setWatermark();
    }

    @Override
    public void onClick(View view) {
        int clickId = view.getId();
        if (clickId == R.id.createBack) {
            Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
        } else if (clickId == R.id.createShare) {
            shareImage();
        } else if (clickId == R.id.createWallpaper) {
            Toast.makeText(this, "createWallpaper", Toast.LENGTH_SHORT).show();
        } else if (clickId == R.id.createDownload) {
            easyAccess.singleView(MainActivity.this, "");
            easyAccess.getImageResource(saveScreen);
            easyAccess.imageSave(false);
        } else if (clickId == R.id.pictureListId) {
            openPictureList();
        } else if (clickId == R.id.backgroundBack) {
            stopAnimation(backgroundList);
        } else if (clickId == R.id.galleryOpenId) {
            openGallery();
        } else if (clickId == R.id.textEditId) {
            opnEditText();
        } else if (clickId == R.id.closeEditText) {
            closeKeyboard();
            stopAnimation(editLayout);
        } else if (clickId == R.id.cleanEditText) {
            messageEdit.setText("");
            whoSayEdit.setText("");
        } else if (clickId == R.id.submitEditText) {
            setText();
        } else if (clickId == R.id.editRandomTxt) {
            setRandomText();
        } else if (clickId == R.id.textSizeId) {
            openTextSize();
        } else if (clickId == R.id.textSizeClose) {
            stopAnimation(textSizeLayout);
        } else if (clickId == R.id.colorId) { //color gradient
            setColor();
        } else if (clickId == R.id.colorBackground) {
            backgroundColorBar.setVisibility(View.VISIBLE);
            mainGradientCreate.setVisibility(View.VISIBLE);
            textColorBar.setVisibility(View.INVISIBLE);
            mainColorText.setVisibility(View.INVISIBLE);

        } else if (clickId == R.id.colorText) {
            textColorBar.setVisibility(View.VISIBLE);
            mainColorText.setVisibility(View.VISIBLE);
            backgroundColorBar.setVisibility(View.INVISIBLE);
            mainGradientCreate.setVisibility(View.INVISIBLE);
        } else if (clickId == R.id.addColor1) {
            addColorId = 1;
            setAddImage(addColorId);
        } else if (clickId == R.id.addColor2) {
            addColorId = 2;
            setAddImage(addColorId);
        } else if (clickId == R.id.addColor3) {
            addColorId = 3;
            setAddImage(addColorId);
        } else if (clickId == R.id.applyButton) {
            setGradientBackground(gradientBackground);
        } else if (clickId == R.id.randomGradientId) {
            setRandomGradient();
        } else if (clickId == R.id.multiColorText) {
            multiColorText();
        } else if (clickId == R.id.colorClose) {

            stopAnimation(colorLayout);

        } else if (clickId == R.id.propertyId) {
            setProperty();
        } else if (clickId == R.id.propertyClose) {
            stopAnimation(propertyLayout);
        } else if (clickId == R.id.shadowId) {
            setShadow();
        } else if (clickId == R.id.shadowClose) {
            stopAnimation(shadowLayout);
        } else if (clickId == R.id.fontListId) {
            setFont();
        } else if (clickId == R.id.fontClose) {
            stopAnimation(fontLayout);
        } else if (clickId == R.id.effectId) {
            setEffect();
        } else if (clickId == R.id.effectClose) {
            stopAnimation(effectLayout);
        } else if (clickId == R.id.filterId) {
            setFilter();
        } else if (clickId == R.id.filterClose) {
            stopAnimation(filterLayout);
        } else if (clickId == R.id.filterDisable) {
            bitmap = ((BitmapDrawable) fakeImageView.getDrawable()).getBitmap();
            canvasBackgroundImage.setImageBitmap(bitmap);
        } else if (clickId == R.id.filterSubmit) {
            bitmap = ((BitmapDrawable) canvasBackgroundImage.getDrawable()).getBitmap();
            canvasBackgroundImage.setImageBitmap(bitmap);
        }else if (clickId == R.id.textBold) {
            setTextStyle(Typeface.BOLD);
        } else if (clickId == R.id.textItalic) {
            setTextStyle(Typeface.ITALIC);
        } else if (clickId == R.id.textBoldItalic) {
            setTextStyle(Typeface.BOLD_ITALIC);
        } else if (clickId == R.id.flip1) {
            bitmap = ((BitmapDrawable) canvasBackgroundImage.getDrawable()).getBitmap();
            canvasBackgroundImage.setImageBitmap(imageFilter.flipImage(bitmap, 1));
        } else if (clickId == R.id.flip2) {
            bitmap = ((BitmapDrawable) canvasBackgroundImage.getDrawable()).getBitmap();
            canvasBackgroundImage.setImageBitmap(imageFilter.flipImage(bitmap, 2));
        } else if (clickId == R.id.letterSpaceMinus) {
            letterSpacingNumber = spacingClass.letterSpacing(letterSpacingNumber = (letterSpacingNumber - 0.02f));
            letterSpace = spacingClass.letterSpacingCount(--letterSpace);
            letterAndLineSpacing();
        } else if (clickId == R.id.letterSpacePlus) {
            letterSpacingNumber = spacingClass.letterSpacing(letterSpacingNumber = (letterSpacingNumber + 0.02f));
            letterSpace = spacingClass.letterSpacingCount(++letterSpace);
            letterAndLineSpacing();
        } else if (clickId == R.id.lineSpaceMinus) {
            lineSpacingNumber = spacingClass.lineSpacing(lineSpacingNumber = (lineSpacingNumber - 0.2f));
            lineSpace = spacingClass.lineSpacingCount(--lineSpace);
            letterAndLineSpacing();
        } else if (clickId == R.id.lineSpacePlus) {
            lineSpacingNumber = spacingClass.lineSpacing(lineSpacingNumber = (lineSpacingNumber + 0.2f));
            lineSpace = spacingClass.lineSpacingCount(++lineSpace);
            letterAndLineSpacing();
        }

        if(clickId == R.id.watermarkSave){
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        }
    }
    private void setWatermark(){

        //find watermark
        watermarkGroup = findViewById(R.id.watermarkGroup);
        watermarkSave = findViewById(R.id.watermarkSave);
        watermarkSwitch = findViewById(R.id.watermarkSwitch);
        waterImage1 = findViewById(R.id.waterImage1);
        waterImage2 = findViewById(R.id.waterImage2);
        waterImage3 = findViewById(R.id.waterImage3);
        watermarkEdit = findViewById(R.id.watermarkEdit);
        watermarkFontGridView = findViewById(R.id.watermarkFontGridView);
        watermarkColor = findViewById(R.id.watermarkColor);
        watermarkOpacity = findViewById(R.id.watermarkOpacity);
        watermarkClose = findViewById(R.id.watermarkClose);
        watermarkCloseIcon = findViewById(R.id.watermarkCloseIcon);

        //Listener
        watermarkSave.setOnClickListener(this);
        waterImage1.setOnClickListener(this);
        waterImage2.setOnClickListener(this);
        waterImage3.setOnClickListener(this);
        watermarkClose.setOnClickListener(this);


        //visible or invisible
        watermarkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    watermarkGroup.setVisibility(View.VISIBLE);
                }else {
                    watermarkGroup.setVisibility(View.INVISIBLE);
                }
            }
        });

        // add run time text
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                watermarkText.setText(watermarkEdit.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        };
        watermarkEdit.addTextChangedListener(textWatcher);

    }

    private void letterAndLineSpacing() {
        letterSpaceCount.setText(String.valueOf(letterSpace));
        lineSpaceCount.setText(String.valueOf(lineSpace));
        mainTextId.setLetterSpacing(letterSpacingNumber);
        mainSubText.setLetterSpacing(letterSpacingNumber);
        mainTextId.setLineSpacing(0, lineSpacingNumber);

    }

    private void setTextStyle(int id) {
        mainTextId.setTypeface(null, id);
        mainSubText.setTypeface(null, id);
        firstComma.setTypeface(null, id);
        secondComma.setTypeface(null, id);
        int draw = R.drawable.text_style_background, color = Color.TRANSPARENT;
        if (id == 1) {
            textItalic.setBackgroundColor(color);
            textBoldItalic.setBackgroundColor(color);
            italic = 0;
            boldItalic = 0;
            if (bold == 0) {
                textBold.setBackground(getDrawable(draw));
                bold = 1;
            } else {
                setTextStyle(0);
                textBold.setBackgroundColor(color);
                bold = 0;
            }
        } else if (id == 2) {
            textBold.setBackgroundColor(color);
            textBoldItalic.setBackgroundColor(color);
            bold = 0;
            boldItalic = 0;
            if (italic == 0) {
                textItalic.setBackground(getDrawable(draw));
                italic = 1;
            } else {
                setTextStyle(0);
                textItalic.setBackgroundColor(color);
                italic = 0;
            }
        } else if (id == 3) {
            textBold.setBackgroundColor(color);
            textItalic.setBackgroundColor(color);
            bold = 0;
            italic = 0;
            if (boldItalic == 0) {
                textBoldItalic.setBackground(getDrawable(draw));
                boldItalic = 1;
            } else {
                setTextStyle(0);
                textBoldItalic.setBackgroundColor(color);
                boldItalic = 0;
            }
        }
    }

    private void setEffect() {
        startAnimation(effectLayout);
        imageBlurSeekBar = findViewById(R.id.imageBlur);
        effectClose = findViewById(R.id.effectClose);
        effectCloseIcon = findViewById(R.id.effectCloseIcon);
        effectClose.setOnClickListener(this);
        bitmap = ((BitmapDrawable) canvasBackgroundImage.getDrawable()).getBitmap();

        imageBlurSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                canvasBackgroundImage.setImageBitmap(imageFilter.blurRenderScript(getApplicationContext(), bitmap, (progress / 4)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setFilter() {
        startAnimation(filterLayout);
        bitmap = ((BitmapDrawable) canvasBackgroundImage.getDrawable()).getBitmap();
        ImageView filterDisable = findViewById(R.id.filterDisable);
        ImageView filterSubmit = findViewById(R.id.filterSubmit);
        colorFilterGridView = findViewById(R.id.colorFilterGridView);
        filterClose = findViewById(R.id.filterClose);
        filterCloseIcon = findViewById(R.id.filterCloseIcon);
        filterSeekBar = findViewById(R.id.filterSeekBar);
        filterDisable.setOnClickListener(this);
        filterSubmit.setOnClickListener(this);
        filterClose.setOnClickListener(this);
        FilterAdapter colorFilterAdapter = new FilterAdapter();
        colorFilterGridView.setAdapter(colorFilterAdapter);

        colorFilterGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int color = Color.parseColor(filterColorCode[position]);
                canvasBackgroundImage.setImageBitmap(imageFilter.applyShadingFilter(bitmap, color));
            }
        });

        filterSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                canvasBackgroundImage.setImageBitmap(imageFilter.applyTintEffect(bitmap, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void shareImage() {
        int[] location = new int[2];
        createShare.getLocationOnScreen(location);
        p = new Point();
        p.x = location[0];
        p.y = location[1];
        easyAccess.showPopup(MainActivity.this, p, "positionString");
        easyAccess.getImageResource(saveScreen);

    }

    private void setFont() {
        startAnimation(fontLayout);
        FontClass fontClass = new FontClass();
        ListView fontListView = findViewById(R.id.fontListView);
        fontClose = findViewById(R.id.fontClose);
        fontCloseIcon = findViewById(R.id.fontCloseIcon);
        fontClose.setOnClickListener(this);
        fontListView.setAdapter(fontClass);

        fontListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), fontArray[position]);
                mainTextId.setTypeface(typeface);
                mainSubText.setTypeface(typeface);
            }
        });

    }

    private void setShadow() {
        startAnimation(shadowLayout);
        //shadowSwitch = findViewById(R.id.shadowSwitch);
        shadowColor = findViewById(R.id.shadowColor);
        shadowLeftRight = findViewById(R.id.shadowLeftRight);
        shadowUpDown = findViewById(R.id.shadowUpDown);
        shadowBlur = findViewById(R.id.shadowBlur);
        shadowClose = findViewById(R.id.shadowClose);
        shadowCloseIcon = findViewById(R.id.shadowCloseIcon);
        setShadow(blur, leftRight, upDown, color);
        shadowClose.setOnClickListener(this);
        if (shadowLeftRight.getProgress() == 0) {
            shadowLeftRight.setProgress(shadowLeftRight.getMax() / 2);
        }
        if (shadowUpDown.getProgress() == 0) {
            shadowUpDown.setProgress(shadowUpDown.getMax() / 2);
        }
        if (shadowBlur.getProgress() == 0) {
            shadowBlur.setProgress(4);
        }

        shadowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean shadowSwitch) {
                if (shadowSwitch) {
                    setShadow(blur, leftRight, upDown, color);
                } else {
                    mainTextId.setShadowLayer(0, leftRight, upDown, color);
                    mainSubText.setShadowLayer(0, leftRight, upDown, color);
                    firstComma.setShadowLayer(0, leftRight, upDown, color);
                    secondComma.setShadowLayer(0, leftRight, upDown, color);
                }
            }
        });
        shadowColor.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {

            @Override
            public void onColorChangeListener(int i) {
                color = i;
                setShadow(blur, leftRight, upDown, color);
            }
        });
        shadowLeftRight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int upDate;
                if (i < 10) {
                    upDate = (10 - i);
                    leftRight = upDate;
                } else if (i > 10) {
                    upDate = -(10 - i);
                    leftRight = -upDate;
                }
                setShadow(blur, leftRight, upDown, color);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        shadowUpDown.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int upDate;
                if (i < 10) {
                    upDate = (10 - i);
                    upDown = upDate;
                } else if (i > 10) {
                    upDate = -(10 - i);
                    upDown = -upDate;
                }
                setShadow(blur, leftRight, upDown, color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        shadowBlur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                blur = i / 2;
                setShadow(blur, leftRight, upDown, color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    private void setProperty() {
        startAnimation(propertyLayout);

        textBold = findViewById(R.id.textBold);
        textItalic = findViewById(R.id.textItalic);
        textBoldItalic = findViewById(R.id.textBoldItalic);

        flip1 = findViewById(R.id.flip1);
        flip2 = findViewById(R.id.flip2);

        letterSpaceMinus = findViewById(R.id.letterSpaceMinus);
        letterSpaceCount = findViewById(R.id.letterSpaceCount);
        letterSpacePlus = findViewById(R.id.letterSpacePlus);

        lineSpaceMinus = findViewById(R.id.lineSpaceMinus);
        lineSpaceCount = findViewById(R.id.lineSpaceCount);
        lineSpacePlus = findViewById(R.id.lineSpacePlus);

        customizeWidth = findViewById(R.id.customizeWidth);
        customizeHeight = findViewById(R.id.customizeHeight);
        customizeColor = findViewById(R.id.customizeColor);
        customizeOpacity = findViewById(R.id.customizeOpacity);

        commaSwitch = findViewById(R.id.commaSwitch);
        whoSaySwitch = findViewById(R.id.whoSaySwitch);
        propertyClose = findViewById(R.id.propertyClose);
        propertyCloseIcon = findViewById(R.id.propertyCloseIcon);

        textBold.setOnClickListener(this);
        textItalic.setOnClickListener(this);
        textBoldItalic.setOnClickListener(this);
        flip1.setOnClickListener(this);
        flip2.setOnClickListener(this);

        letterSpaceMinus.setOnClickListener(this);
        letterSpacePlus.setOnClickListener(this);
        lineSpaceMinus.setOnClickListener(this);
        lineSpacePlus.setOnClickListener(this);

        propertyClose.setOnClickListener(this);


        customizeWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setMargins(progress, customizeHeight.getProgress(), progress, customizeHeight.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (customizeOpacity.getProgress() < 15) {
                    reSizeCanvas.setBackgroundColor(Color.parseColor("#9A222020"));
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                reSizeCanvas.setBackgroundColor(customizeColor.getColor());
                reSizeCanvas.getBackground().setAlpha(customizeOpacity.getProgress());

            }
        });
        customizeHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setMargins(customizeWidth.getProgress(), progress, customizeWidth.getProgress(), progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (customizeOpacity.getProgress() < 15) {
                    reSizeCanvas.setBackgroundColor(Color.parseColor("#9A222020"));
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                reSizeCanvas.setBackgroundColor(customizeColor.getColor());
                reSizeCanvas.getBackground().setAlpha(customizeOpacity.getProgress());
            }
        });
        customizeColor.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int color) {
                reSizeCanvas.setBackgroundColor(color);
                reSizeCanvas.getBackground().setAlpha(customizeOpacity.getProgress());
                if (customizeOpacity.getProgress() < 15) {
                    customizeOpacity.setProgress(15);
                }
            }
        });
        customizeOpacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int opacity, boolean fromUser) {
                reSizeCanvas.getBackground().setAlpha(opacity);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        commaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean switchB) {
                if (switchB) {
                    firstComma.setVisibility(View.VISIBLE);
                    secondComma.setVisibility(View.VISIBLE);
                } else {
                    firstComma.setVisibility(View.INVISIBLE);
                    secondComma.setVisibility(View.INVISIBLE);
                }
            }
        });
        whoSaySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean switchB) {
                if (switchB) {
                    mainSubText.setVisibility(View.VISIBLE);
                } else {

                    mainSubText.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    public void setMargins(int l, int t, int r, int b) {
        if (reSizeCanvas.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) reSizeCanvas.getLayoutParams();
            p.setMargins(l, t, r, b);
            reSizeCanvas.requestLayout();
        }
    }


    //<-----------------------------------------------------color section---------------------------------->
    private void setColor() {
        startAnimation(colorLayout);

        //gradient
        colorBackground = findViewById(R.id.colorBackground);
        colorText = findViewById(R.id.colorText);
        backgroundColorBar = findViewById(R.id.backgroundColorBar);
        solidOpacitySeekBar = findViewById(R.id.solidOpacitySeekBar);
        textColorBar = findViewById(R.id.textColorBar);
        mainGradientCreate = findViewById(R.id.mainGradientCreate);
        mainColorText = findViewById(R.id.mainColorText);
        backgroundColorSeekBar = findViewById(R.id.backgroundColorSeekBar);
        addColor1 = findViewById(R.id.addColor1);
        addColor2 = findViewById(R.id.addColor2);
        addColor3 = findViewById(R.id.addColor3);
        addImage1 = findViewById(R.id.addImage1);
        addImage2 = findViewById(R.id.addImage2);
        addImage3 = findViewById(R.id.addImage3);
        gradientPreviewId = findViewById(R.id.gradientPreviewId);
        colorPositionChange = findViewById(R.id.colorPositionChange);
        colorGridView = findViewById(R.id.colorGridView);
        gradientOpacity = findViewById(R.id.gradientOpacity);
        gradientSpinner = findViewById(R.id.gradientSpinner);
        gradientAutoApply = findViewById(R.id.gradientAutoApply);
        applyButton = findViewById(R.id.applyButton);
        GradientGridView = findViewById(R.id.GradientGridView);
        randomGradientId = findViewById(R.id.randomGradientId);

        colorBackground.setOnClickListener(this);
        colorText.setOnClickListener(this);
        addColor1.setOnClickListener(this);
        addColor2.setOnClickListener(this);
        addColor3.setOnClickListener(this);
        applyButton.setOnClickListener(this);
        randomGradientId.setOnClickListener(this);

        GradientGridView.setAdapter(gradientBackgroundClass);//constance gradient
        colorGridView.setAdapter(singleColorClass); //own gradient

        backgroundColorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int color) {
                canvasBackground.setBackgroundColor(color);
                canvasBackground.getBackground().setAlpha((solidOpacitySeekBar.getProgress()));
            }
        });

        solidOpacitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                canvasBackground.getBackground().setAlpha(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //gradient opacity set seekBar
        gradientOpacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                canvasBackground.getBackground().setAlpha(progress);
                gradientPreviewId.getBackground().setAlpha(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //gradient orientation spinner adapter
        ArrayAdapter spinner = new ArrayAdapter(this, R.layout.spinner_item, orientationList);
        spinner.setDropDownViewResource(R.layout.spinner_list);
        gradientSpinner.setAdapter(spinner);


        textColorSeekBar = findViewById(R.id.textColorSeekBar);
        multiColorText = findViewById(R.id.multiColorText);
        textShadowSwitch = findViewById(R.id.textShadowSwitch);
        colorClose = findViewById(R.id.colorClose);
        colorCloseIcon = findViewById(R.id.colorCloseIcon);
        multiColorText.setOnClickListener(this);
        textShadowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setShadow(blur, leftRight, upDown, color);
                } else {
                    setShadow(0, leftRight, upDown, color);
                }
            }
        });

        colorClose.setOnClickListener(this);
        textColorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int color) {
                mainTextId.setText(mainTextId.getText().toString());
                mainSubText.setText(mainSubText.getText().toString());
                mainTextId.setTextColor(color);
                mainSubText.setTextColor(color);
                firstComma.setTextColor(color);
                secondComma.setTextColor(color);
            }
        });


    }

    public void setShadow(float mBlur, int mLeftRight, int mUpDown, int mColor) {
        shadowSwitch.setChecked(true);
        mainTextId.setShadowLayer(mBlur, mLeftRight, mUpDown, mColor);
        mainSubText.setShadowLayer(mBlur, mLeftRight, mUpDown, mColor);
        firstComma.setShadowLayer(mBlur, 0, 0, mColor);
        secondComma.setShadowLayer(mBlur, 0, 0, mColor);

    }

    //single color selected item icon set
    private void setAddImage(int position) {
        if (position == 1) {
            addImage1.setVisibility(View.VISIBLE);
            addImage2.setVisibility(View.INVISIBLE);
            addImage3.setVisibility(View.INVISIBLE);
        } else if (position == 2) {
            addImage2.setVisibility(View.VISIBLE);
            addImage1.setVisibility(View.INVISIBLE);
            addImage3.setVisibility(View.INVISIBLE);
        } else if (position == 3) {
            addImage3.setVisibility(View.VISIBLE);
            addImage1.setVisibility(View.INVISIBLE);
            addImage2.setVisibility(View.INVISIBLE);
        }
    }

    //textView text multi color
    public void multiColorText() {
        String mainColorfulString, mainSubString;
        mainColorfulString = mainTextId.getText().toString();
        mainSubString = mainSubText.getText().toString();

        Spannable mainSpan, subSpin;
        mainSpan = new SpannableString(mainColorfulString);
        subSpin = new SpannableString(mainSubString);

        for (int i = 0, len = mainColorfulString.length(); i < len; i++) {
            mainSpan.setSpan(new ForegroundColorSpan(getRandomColor()), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        for (int i = 0, len = mainSubString.length(); i < len; i++) {
            mainSpan.setSpan(new ForegroundColorSpan(getRandomColor()), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            subSpin.setSpan(new ForegroundColorSpan(getRandomColor()), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        mainTextId.setText(mainSpan);
        mainSubText.setText(subSpin);
        firstComma.setTextColor(getRandomColor());
        secondComma.setTextColor(getRandomColor());
    }

    //background random gradient set
    private void setRandomGradient() {
        if (gradientOpacity.getProgress() < 100) {
            gradientOpacity.setProgress(100);
        }
        randomGradient = new GradientDrawable();

        randomColorList = new int[]{getRandomColor(), getRandomColor(), getRandomColor()};
        randomGradient.setColors(randomColorList);

        canvasBackground.setBackground(randomGradient);
        canvasBackground.getBackground().setAlpha(gradientOpacity.getProgress());


    }

    //gradient set into background
    public void setGradientBackground(GradientDrawable solidGradient) {
        if (orientation == 0) {
            solidGradient.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        } else if (orientation == 1) {
            solidGradient.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        } else if (orientation == 2) {
            solidGradient.setOrientation(GradientDrawable.Orientation.BR_TL);
        } else if (orientation == 3) {
            solidGradient.setOrientation(GradientDrawable.Orientation.BL_TR);
        } else if (orientation == 4) {
            solidGradient.setOrientation(GradientDrawable.Orientation.TR_BL);
        }
        solidGradient.setColors(colorList);
        solidGradient.setAlpha(gradientOpacity.getProgress());
        if (colorList[0] != 0) {
            canvasBackground.setBackground(solidGradient);
        }


    }

    //gradient set into preview
    public void setGradientPreview(GradientDrawable solidGradient) {
        if (orientation == 0) {
            solidGradient.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        } else if (orientation == 1) {
            solidGradient.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        } else if (orientation == 2) {
            solidGradient.setOrientation(GradientDrawable.Orientation.BR_TL);
        } else if (orientation == 3) {
            solidGradient.setOrientation(GradientDrawable.Orientation.BL_TR);
        } else if (orientation == 4) {
            solidGradient.setOrientation(GradientDrawable.Orientation.TR_BL);
        }
        solidGradient.setColors(colorList);
        solidGradient.setAlpha(gradientOpacity.getProgress());

        if (colorList[0] != 0) {
            gradientPreviewId.setBackground(solidGradient);
        }
    }

    //random color set
    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    //background gradient set
    class GradientBackgroundClass extends BaseAdapter {
        @Override
        public int getCount() {
            return 99;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.gradient_count_flow, parent, false);
            }
            final LinearLayout gradientFlow = convertView.findViewById(R.id.gradientFlow);
            final TextView gradientCount = convertView.findViewById(R.id.gradientCount);
            constanceGradient = new GradientDrawable();
            for (int i = 0; i < 99; i++) {
                if (position == i) {
                    if (position % 2 == 0) {
                        gradientColorList = new int[]{getRandomColor(), getRandomColor()};
                        constanceGradient.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                    } else {
                        if (position % 3 == 0) {
                            constanceGradient.setOrientation(GradientDrawable.Orientation.TL_BR);
                        } else {
                            constanceGradient.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                            //SWEEP_GRADIENT,
                        }
                        gradientColorList = new int[]{getRandomColor(), getRandomColor(), getRandomColor()};

                    }

                }
            }
            constanceGradient.setColors(gradientColorList);

            gradientFlow.setBackground(constanceGradient);

            gradientCount.setText(String.valueOf(1 + position));
            gradientFlow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (gradientOpacity.getProgress() < 100) {
                        gradientOpacity.setProgress(100);
                    }
                    canvasBackground.setBackground(gradientFlow.getBackground());
                    canvasBackground.getBackground().setAlpha(gradientOpacity.getProgress());

                }
            });


            return convertView;
        }
    }

    //single color class
    class SingleColorClass extends BaseAdapter {
        //int colorList[] = {0, 0};

        @Override
        public int getCount() {
            return 99;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.gradient_count_flow, parent, false);
            }

            final LinearLayout gradientFlow = convertView.findViewById(R.id.gradientFlow);
            TextView gradientCount = convertView.findViewById(R.id.gradientCount);

            gradientFlow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 190));

            for (int i = 0; i < 99; i++) {
                if (position == i) {
                    int color = getRandomColor();
                    gradientFlow.setBackgroundColor(color);
                }
            }


            gradientFlow.setBackground(gradientFlow.getBackground());
            gradientCount.setText(String.valueOf(position));
            gradientBackground = new GradientDrawable();
            gradientPreview = new GradientDrawable();


            gradientFlow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (gradientOpacity.getProgress() < 100) {
                        gradientOpacity.setProgress(100);
                    }
                    if (colorPositionChange.isChecked() && addColorId == 1) {
                        addColor1.setBackground(gradientFlow.getBackground());
                        addColorId = 2;
                        setAddImage(1);
                    } else if (addColorId == 1) {
                        addColor1.setBackground(gradientFlow.getBackground());
                        setAddImage(1);
                    } else if (colorPositionChange.isChecked() && addColorId == 2) {
                        addColor2.setBackground(gradientFlow.getBackground());
                        addColorId = 3;
                        setAddImage(2);
                    } else if (addColorId == 2) {
                        addColor2.setBackground(gradientFlow.getBackground());
                        setAddImage(2);
                    } else if (colorPositionChange.isChecked() && addColorId == 3) {
                        addColor3.setBackground(gradientFlow.getBackground());
                        addColorId = 1;
                        setAddImage(3);
                    } else if (addColorId == 3) {
                        addColor3.setBackground(gradientFlow.getBackground());
                        setAddImage(3);
                    }

                    // single color box
                    int color1 = Color.TRANSPARENT, color2 = Color.TRANSPARENT, color3 = Color.TRANSPARENT;
                    Drawable backgroundColor1, backgroundColor2, backgroundColor3;
                    backgroundColor1 = addColor1.getBackground();
                    backgroundColor2 = addColor2.getBackground();
                    backgroundColor3 = addColor3.getBackground();

                    if (backgroundColor1 instanceof ColorDrawable)
                        color1 = ((ColorDrawable) backgroundColor1).getColor();

                    if (backgroundColor2 instanceof ColorDrawable)
                        color2 = ((ColorDrawable) backgroundColor2).getColor();

                    if (backgroundColor3 instanceof ColorDrawable)
                        color3 = ((ColorDrawable) backgroundColor3).getColor();

                    colorList = new int[]{color1, color2, color3};
                    if (gradientAutoApply.isChecked()) {
                        setGradientBackground(gradientBackground);
                    }
                    setGradientPreview(gradientPreview);
                }
            });

            //orientation select spinner

            gradientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (position != 0) {
                        orientation = position;
                        if (gradientAutoApply.isChecked()) {
                            setGradientBackground(gradientBackground);
                        }
                        setGradientPreview(gradientPreview);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            return convertView;
        }
    }


    private void openTextSize() {
        startAnimation(textSizeLayout);
        fontSizeSeekBar = findViewById(R.id.textSizeSeekBar);
        textSizeClose = findViewById(R.id.textSizeClose);
        textSizeCloseIcon = findViewById(R.id.textSizeCloseIcon);
        textSizeClose.setOnClickListener(this);
        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                fontSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "" + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setText() {
        closeKeyboard();
        String mainText = messageEdit.getText().toString();
        String whoSay = whoSayEdit.getText().toString();
        if (mainText.isEmpty()) {
            Toast.makeText(this, "Please Write Something", Toast.LENGTH_SHORT).show();
        } else {
            mainTextId.setText(mainText);
            stopAnimation(editLayout);
            closeKeyboard();
        }
        if (!whoSay.isEmpty()) {
            mainSubText.setText("- " + whoSay);
        } else {
            mainSubText.setText("");
        }
    }

    private void fontSize(int fontSize) {
        mainTextId.setTextSize(fontSize);
        mainSubText.setTextSize(fontSize - 5);
        firstComma.setTextSize(fontSize + 5);
        secondComma.setTextSize(fontSize + 4);

    }

    private void opnEditText() {
        startAnimation(editLayout);
        ImageView closeEditText, cleanEditText, submitEditText, editRandomTxt;
        closeEditText = findViewById(R.id.closeEditText);
        cleanEditText = findViewById(R.id.cleanEditText);
        submitEditText = findViewById(R.id.submitEditText);
        editRandomTxt = findViewById(R.id.editRandomTxt);
        messageEdit = findViewById(R.id.messageEditText);
        whoSayEdit = findViewById(R.id.whoSayId);
        messageEdit.setText(mainTextId.getText().toString());
        if (!whoSayEdit.getText().toString().isEmpty()) {
            whoSayEdit.setText(mainSubText.getText().toString().substring(2, mainSubText.length()));
        }

        closeEditText.setOnClickListener(this);
        cleanEditText.setOnClickListener(this);
        submitEditText.setOnClickListener(this);
        editRandomTxt.setOnClickListener(this);
    }

    private void setRandomText() {
        position++;
        randomPosition = randomNum + position;
        if (randomPosition <= whatSay.length - 1) {
            messageEdit.setText(whatSay[(randomPosition)]);
            whoSayEdit.setText(whoSay[randomPosition]);
        } else if (randomPosition >= whatSay.length) {
            messageEdit.setText(whatSay[5]);
            whoSayEdit.setText(whoSay[5]);
            position = -1;
            randomNum = 0;

        }

        //messageEdit.setText(whatSay[position]);
        //whoSayEdit.setText(whoSay[position]);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                stopAnimation(backgroundList);
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                canvasBackgroundImage.setImageBitmap(bitmap);
                fakeImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //<-------------------------------------------picture section------------------------------->
    private void openGallery() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 21) {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        } else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    private void openPictureList() {
        startAnimation(backgroundList);
        pictureListGridView = findViewById(R.id.backgroundGridView);
        backgroundBack = findViewById(R.id.backgroundBack);
        galleryOpen = findViewById(R.id.galleryOpenId);
        backgroundBack.setOnClickListener(this);
        galleryOpen.setOnClickListener(this);
        SelectBackgroundClass selectBackgroundClass = new SelectBackgroundClass();
        pictureListGridView.setAdapter(selectBackgroundClass);
        pictureListGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                canvasBackgroundImage.setImageResource(backgroundImage[position]);
                fakeImageView.setImageResource(backgroundImage[position]);
                stopAnimation(backgroundList);
            }
        });
    }

    class SelectBackgroundClass extends BaseAdapter {

        @Override
        public int getCount() {
            return backgroundImage.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.background_flow_layout, parent, false);
            }
            ImageView imageView = convertView.findViewById(R.id.flowImageView);
            imageView.setImageResource(backgroundImage[position]);

            return convertView;
        }
    }


    private void startAnimation(View view) {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_down_top);
        view.startAnimation(animation);
        view.setVisibility(View.VISIBLE);
        mainControl.setVisibility(View.INVISIBLE);


    }

    private void stopAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_top_down);
        view.startAnimation(animation);
        view.setVisibility(View.INVISIBLE);
        mainControl.setVisibility(View.VISIBLE);

    }

    private void removeView() {
        if (backgroundList.getVisibility() == View.VISIBLE) {
            stopAnimation(backgroundList);
        } else if (editLayout.getVisibility() == View.VISIBLE) {
            stopAnimation(editLayout);
        } else if (textSizeLayout.getVisibility() == View.VISIBLE) {
            stopAnimation(textSizeLayout);
        } else if (colorLayout.getVisibility() == View.VISIBLE) {
            stopAnimation(colorLayout);
        } else if (fontLayout.getVisibility() == View.VISIBLE) {
            stopAnimation(fontLayout);
        } else if (effectLayout.getVisibility() == View.VISIBLE) {
            stopAnimation(effectLayout);
        } else if (filterLayout.getVisibility() == View.VISIBLE) {
            stopAnimation(filterLayout);
        } else if (propertyLayout.getVisibility() == View.VISIBLE) {
            stopAnimation(propertyLayout);
        } else if (shadowLayout.getVisibility() == View.VISIBLE) {
            stopAnimation(shadowLayout);
        }
    }

    private void whiteStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), android.R.color.white));// set status background white
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    class FontClass extends BaseAdapter {

        @Override
        public int getCount() {
            return fontArray.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.font_flow_layout, parent, false);
            }
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), fontArray[position]);

            TextView fontCount, fontList;
            fontCount = convertView.findViewById(R.id.fontCount);
            fontList = convertView.findViewById(R.id.fontList);
            fontList.setText(mainTextId.getText().toString());
            fontList.setTypeface(typeface);
            fontCount.setTypeface(typeface);
            fontCount.setText(Integer.toString(position + 1));

            return convertView;
        }
    }

    class FilterAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return filterColorCode.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.filter_layout, parent, false);
            }
            ImageView imageView = convertView.findViewById(R.id.flowFilterImage);
            TextView textView = convertView.findViewById(R.id.flowFilterName);
            bitmap = ((BitmapDrawable) fakeImageView.getDrawable()).getBitmap();
            imageView.setImageBitmap(bitmap);
            int color = Color.parseColor(filterColorCode[position]);
            textView.setBackgroundColor(color);
            if (position == 5 || position == 19 || position == 37 || position == 41 || position == 43 || position == 54 || position == 83 || position == 84) {
                textView.setTextColor(Color.BLACK);
            }
            textView.setText(filterColorName[position]);


            return convertView;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        removeView();
    }
}

class SpacingClass {
    public float letterSpacing(float space) {
        if (space < -0.15) {
            space = -0.15f;
            return space;
        } else if (space > 1) {
            space = 1;
            return space;
        } else {
            return space;
        }
    }

    public int letterSpacingCount(int count) {
        if (count < -5) {
            count = -5;
            return count;
        } else if (count > 50) {
            count = 50;
            return count;
        } else {
            return count;
        }
    }


    public float lineSpacing(float space) {
        if (space < 0.2) {
            space = 0.2f;
            return space;
        } else if (space > 10) {
            space = 10;
            return space;
        } else {
            return space;
        }
    }

    public int lineSpacingCount(int count) {
        if (count < -5) {
            count = -5;
            return count;
        } else if (count > 45) {
            count = 45;
            return count;
        } else {
            return count;
        }
    }
}