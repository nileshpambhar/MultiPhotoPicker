# MultiPhotoPicker

[![](https://jitpack.io/v/nileshpambhar/MultiPhotoPicker.svg)](https://jitpack.io/#nileshpambhar/MultiPhotoPicker)


**Screen1**

![Alt text](https://github.com/nileshpambhar/MultiPhotoPicker/blob/master/Screenshots/Screen1.png "Album list")

**Screen2**

![Alt text](https://github.com/nileshpambhar/MultiPhotoPicker/blob/master/Screenshots/Screen2.png "Photo list")

## How to...

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Step 2. Add the dependency
  
  	dependencies {
	        compile 'com.github.nileshpambhar:MultiPhotoPicker:v1.2'
	}


# Usage 
Add below code in your activity
 	
	Intent mIntent = new Intent(this, PickImageActivity.class);
	mIntent.putExtra(PickImageActivity.KEY_LIMIT_MAX_IMAGE, 60);
	mIntent.putExtra(PickImageActivity.KEY_LIMIT_MIN_IMAGE, 3);
	startActivityForResult(mIntent, PickImageActivity.PICKER_REQUEST_CODE);
	
Handle result in onActivityResult method as below...

	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (resultCode == -1 && requestCode == PickImageActivity.PICKER_REQUEST_CODE) {
	    this.pathList = intent.getExtras().getStringArrayList(PickImageActivity.KEY_DATA_RESULT);
            if (this.pathList != null && !this.pathList.isEmpty()) {
                StringBuilder sb=new StringBuilder("");
                for(int i=0;i<pathList.size();i++) {
                    sb.append("Photo"+(i+1)+":"+pathList.get(i));
                    sb.append("\n");
                }
                tvResult.setText(sb.toString()); // here this is textview for sample use...
            }
        }
    }
	
