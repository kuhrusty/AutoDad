# AutoDad

This was my first Android app.  It's pretty dumb; it was just a way for
me to try out Android.  It's also available as a free download in the
Google Play store [here](https://play.google.com/store/apps/details?id=com.kuhrusty.autodad).

The target audience for this is my kids, so that they will always have
easy access to the sort of guidance I would give even when I'm not there
for them.

### INTERESTING STUFF IN THE CODE

- setting the default volume control stream
- playing multiple sounds concurrently (`SoundBoardActivity.java`)

### TO CHECK OUT THE CODE

In Android Studio 3.1.3:

1. File -> New -> Project from Version Control -> GitHub

2. Clone Repository:
   - Repository URL: https://github.com/kuhrusty/AutoDad.git
   - Parent Directory: (whatever you want; probably
     /home/.../AndroidStudioProjects)
   - Directory Name: (whatever you want; probably AutoDad)

   That should check the stuff out and start a Gradle build.  (If you
   get errors about missing .iml files, ignore them--do *not* remove the
   modules it's talking about.)

3. Hit the "Sync Project with Gradle Files" button in the toolbar.  This
   should generate the .iml files it was complaining about.

### TO BUILD & RUN IT

In Android Studio, there may be a rectangular button in the toolbar
below the menu bar which says "app" and which has a green or gray
triangle to the right of it; if you click on that triangle, you might
get a window which lets you choose between your connected device, or an
emulated device (that is, running on a virtual/pretend phone in Android
Studio, not your real phone).  If your phone doesn't show up in that
window, something is wrong.  Choose your phone (plugged in as a USB
device, connected as a camera or a media device... probably camera) and
wait for the "Ask AutoDad 1.0!" screen to show up.

### TO MAKE IT YOUR OWN

To make this something you can install on your own kids' devices for
laughs, you probably want to do the following:

#### 1. Record yourself saying "no" in various ways.

I used my phone to record mine.  Once you have your recordings, and have
trimmed them down to just the bits you want (I forget what tool I used
for that--probably just the Android voice recorder), put them under
`app/src/main/res/raw`.  (I named mine `no1.m4a`, `no2.m4a`, etc., but
you can name them whatever you want, as long as you stick to lower case,
numbers, and underscores.)

#### 2. Replace the list of sounds.

Unfortunately, the list of sounds is hard-coded in two places.  (Hey,
I'm not saying this is *great* code!)  The first is in
`DisplayAnswerActivity.java`, in `nextRand()`, where we're deciding
which "no" to play.  (I only recorded one "yes"; that's chosen in
`playSound()`.)

The second is in `SoundBoardActivity.java`, in `loadSounds()`.  There,
the name of each sound is read from `strings.xml` in
`app/src/main/res/values`, so if one of your sounds is you saying, "Son,
don't make me slap you," you might add this to `strings.xml`:

    <string name="sound_name_slap">Son, don\'t make me slap you.</string>

and then this in `loadSounds()`:

    res.getString(R.string.sound_name_slap)

#### 3. Replace the list of questions with your own.

The list of questions is hard-coded in
`AskPreparedQuestionActivity.java`, in `loadQuestions()`.

(Normally, you don't want to hard-code English strings in your code; you
want to put them in `strings.xml`, similar to the way
`SoundBoardActivity.loadSounds()` was done.)

#### 4. Replace the launch icon with a picture of yourself.

Here's a video on this: https://www.youtube.com/watch?v=SDKwNh0TioE

#### 5. Build and deploy on your kid's device!
