package com.hilatest.androidexample.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.hilatest.androidexample.R;

public class DrawableMutationActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.drawable_mutation);
		
		this.testMutation();
	}

	/**
	 * more details please see article "Drawable Mutations" in resourse
	 * /docs/resources/articles/drawable-mutations.html
	 * 
	 * 	if (book.isFavorite()) {
  			star.setAlpha(255); // opaque     不透明
		} else {
  			star.setAlpha(70); // translucent 透明
		}
	 */
	public void testMutation(){
		/**
		 * 两个绿色的都会变透明。因为他们的state相同
		 */
		Drawable icon_android = ((ImageView)this.findViewById(R.id.unmutate_1)).getDrawable();
		icon_android.setAlpha(70);
		
		/**
		 * 只有上面的会变透明，因为他们的state不同
		 */
		Drawable icon_android_red = ((ImageView)this.findViewById(R.id.mutate_1)).getDrawable();
		icon_android_red.mutate().setAlpha(70);
	}
}
