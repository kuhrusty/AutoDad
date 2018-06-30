# AutoDad

This was my first Android app.  It's pretty dumb; it was just a way for
me to try out Android.

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
