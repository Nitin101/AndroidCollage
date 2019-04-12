package com.example.nitin.nini;

import android.content.Context;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Stage extends GLSurfaceView {

    private float w,h;
    private int screenW, screenH;
    private FloatBuffer vertexBuffer;

    private Texture tex;
    private String img;
    private float xPos,yPos,r;
    Context context;
    public Stage(Context context) {
        super(context);
        this.context = context;
        //img ="goku_ui";
    }

    public void setImage(String imgName){
        this.img = imgName;
        setEGLConfigChooser(8,8,8,8,0,0);
        setRenderer(new MyRenderer());

        float vertices [] = {
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                -0.5f, 0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
        };

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
        tex = new Texture(getResources().getIdentifier(img, "drawable", context.getPackageName()));

    }

    private final class MyRenderer implements GLSurfaceView.Renderer{

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

            // set up alpha blending
            gl.glEnable(GL10.GL_ALPHA_TEST);
            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);

            // Setting 2D env
            gl.glDisable(GL10.GL_DEPTH_TEST);

            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            tex.load(getContext());
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

            gl.glClearColor(0,0,0,1.0f);

//            if(width>height){
//                h = tex.getHeight();
//                w = width * h / height;
//            }
//            else{
//                w = tex.getWidth();
//                h = height *w / width;
//            }

            h = tex.getHeight();
            w = tex.getWidth();

            screenH = height;
            screenW = width;

            xPos = w/2;
            yPos = h/2;
            r = 1;


            gl.glViewport(0,0,screenW,screenH);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glOrthof(0,w,h,0,-1,1);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
        }


        @Override
        public void onDrawFrame(GL10 gl) {
            gl.glClear(GLES10.GL_COLOR_BUFFER_BIT);
            tex.prepare(gl, GL10.GL_CLAMP_TO_EDGE);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
            tex.draw(gl, xPos, yPos, tex.getWidth()*r, tex.getHeight()*r, 0);
        }
    }

}
