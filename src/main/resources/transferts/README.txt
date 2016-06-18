MangAI
@author: Andrew Mantel
@version: 1.15
----------------------------------------------------------------------
-Getting Started-

Note 1: Graphical User Interface specific instructions are denoted by GUI, while Command Line Interface specific instructions are deonted by CLI:.
Note 2: "MangAI folder" refers to the folder you extracted the MangAI distribution archive to. It should contain mangai.jar and mangai_gui.jar.

1) Install Java Runtime Environment. Most computers should already have this installed. You can test if Java is installed by opening a command prompt (Windows) or terminal (Mac/Linux) and running:
   java -version
   MangAI requires at least Java v1.6. It's best to use the latest version of Java. You can download Java from http://www.java.com/en/download/manual.jsp
2) Install ImageMagick. ImageMagick is free software used for image manipulation and can be downloaded from http://www.imagemagick.org/script/binary-releases.php
   See -Setting up ImageMagick- below for more details.
3) Extract the MangAI distribution archive to a folder.
4) Run MangAI.

   GUI: Run mangai_gui.jar
   The easiest way to do this is double-clicking mangai_gui.jar (you may need to right-click->properties to give the jar execute rights).
   You can also open a command prompt/terminal to the MangAI folder and run: java -jar mangai_gui.jar

   CLI: Run mangai.jar
   This is done by opening a command prompt/terminal to the MangAI folder, then run: java -jar mangai.jar
5) Request a trial license.

   GUI: Select Help->Get License. Follow the onscreen instructions.

   CLI: In a command prompt/terminal opened to the MangAI folder, run: java -jar mangai.jar -request_trial "[your_email_address]"
   For example: java -jar mangai.jar -request_trial "johndoe@aol.com"

   See below for more details on licensing.
6) Create a .properties settings file for your device/OS.

   GUI: Under the Settings tab, click the New button, select your ereader device, then click Create.
   Make sure to click the Apply button if you make any changes to the default settings.

   CLI: In a command prompt/terminal opened to the MangAI folder, run: java -jar mangai.jar -create_properties [device_name]
   For example (creating a Kindle DX .properties file): java -jar mangai.jar -create_properties kindledx
   For a full list of acceptable [device_name] values, run the program without any options: java -jar mangai.jar
   You can make changes to the default settings by opening mangai.properties in a text editor.

   The default .properties settings for your device are based on the specs of your ereader and feedback from users. Feel free to change any settings to meet your particular needs.
7) You're ready to process manga.

   GUI: In the "Run" tab, specify the "source" and "Title" of the manga to process.
   Click the Run button to begin processing.

   CLI: In a command prompt/terminal opened to the MangAI folder, run: java -jar mangai.jar
   This will give you a list of the commands you will need to specify to run the program.
   Here is an example of running the program with some settings specified:
   Windows example: java -jar mangai.jar -source C:\\Manga\\SchoolRumble\\ -title "School Rumble" -output_folder C:\\Manga_KDX\\School_Rumble\\
   Mac/Linux example: java -jar mangai.jar -source /Users/john/Manga/SchoolRumble/ -title "School Rumble" -output_folder /Users/john/Manga_KDX/School_Rumble/

   There are several values you can use for source.
   For processing a single manga chapter, set source to:
   - A single folder of image scans
   - A single .zip/.cbz/.rar/.cbr archive of image scans
   - A single .pdf
   For processing a complete series of manga, set source to:
   - A folder containing any number of the above items, all belonging to the same manga series.
   For more details, see -Processing an entire series of manga- below.
8) Let the program run. Depending on how much manga is processing and the speed of your computer, it may take some time to finish. MangAI will display a time estimate based on how much work has been done and how much work is left to do.

----------------------------------------------------------------------
-Feature List-

+Image processing:
 -scale width/height (always maintains original aspect ratio)
 -auto trim white space around a scan
 -auto chop white rows and colums within a scan
 -auto rotate scans to desired landscape/portrait orientation
 -full color, grayscale, or grayscale color table
 -specify # of colors
 -change source image format
 -add border after processing to fill specified width/height
 -specify output jpeg quality
 -sharpen, adjust contrast, auto level, and many other image operations supported by ImageMagick (see -Specifying Other ImageMagick Ops- below for details)
+Supported input sources:
 -Single folder of images
 -Single .zip/.cbz
 -Single .rar/.cbr (Note: Must have compatible unrar program installed on your system. See below for details.)
 -Single .pdf
 -A folder/archive containing any number of the above items, allowing you to process an entire manga series with one command (See -Processing an entire series of manga- below for details)
+Supported input image formats:
 -Pretty much anything that ImageMagick supports (see http://www.imagemagick.org/script/formats.php)
  It's safe to assume that all major image formats are supported: .jpg, .bmp, .gif, .png, etc...
+Supported output formats:
 -Folder of images (any format supported by ImageMagick should be ok)
 -.zip/.cbz
 -.rar/.cbr (Note: Must have compatible rar program installed on your system. See below for details.)
 -pdf (including right-to-left page ordering)
 -epub (including right-to-left page ordering)
 -mobi (including right-to-left page ordering) (Note: Must have KindleGen on your system. See below for details.)
+Binding
 -Binding allows you to combine multiple input sources into a single output
 -Three ways to use binding:
   -Auto bind chapters into volumes based on interpreted volume-chapter info
   -Auto bind groups of a specified size
   -Create a bind file to manually specify which chapters to bind into a single volume
+Auto split landscape 2-page scans
 -Can detect if a landscape scan contains two separate pages and split them accordingly
 -Tries not to split landscape 2-page scans where the two pages form one big image
 -Option to include the unsplit scan along with the two split pages
+Volume-chapter folder interpretation (Manga Publication Model compliant):
 -Automatically interpret volume-chapter info for source folders
 -Sort folders by volume-chapter rather than filename alphabetical comparison
 -Bind chapters of the same volume, and create a fake volume binding for the latest chapters not yet released in tankobon
 -Supports a large variety of source organization, including:
  -Nested folders/archives
  -Determine volume-chapter from parent hierarchy
  -Determine volume from observed bounds of a volume's chapters
  -Detect special chapters, lettered chapters, and string chapters
  -Conflict resolution (both automatic and manual) when multiple sources map to the same volume-chapter
+Volume-chapter-page scan interpretation (Manga Scan Model compliant):
 -Automatically interpret volume-chapter-page info for source scans
 -Sort scans by volume-chapter-page rather than filename alphabetical comparison
 -Detect special pages (front cover, insert, credits, etc.), string chapters/pages, lettered chapters/pages, and more
+Generate a settings file for a variety of ereader devices
 -Optimal settings available for many of the top ereader devices:
  Amazon: Kindle 1, Kindle 2, Kindle 3, Kindle 4, Kindle 5, Kindle Paperwhite, Kindle Touch, Kindle DX, Kindle Fire, Kindle Fire HD 7", Kindle Fire HD 8.9", Kindle Fire HDX 7", Kindle Fire HDX 8.9"
  Apple: iPad, iPad 2, iPad 3, iPad 4, iPad Air, iPad mini, iPad mini with Retina
  Asus: Eee Pad Transformer
  B&N: Nook, Nook Simple Touch, Nook GlowLight, Nook Color, Nook Tablet, Nook HD+
  Google: Nexus 7, Nexus 10
  Sony: PRS-900, PRS-700, PRS-650, PRS-600, PRS-505, PRS-500, PRS-300, PRS-T1, PRS-T2, PRS-T3, Tablet S
  Samsung: Galaxy Tab 10.1, Galaxy Tab 7.7, Galaxy Tab 7
  iRex: Iliad, DR800, DR1000
  Ectaco: jetBook, jetBook Lite
  Kobo: Kobo 1, Kobo 2, Kobo Vox, Kobo Arc, Kobo Arc 7, Kobo Arc 7 HD, Kobo Arc 10 HD, Kobo Aura, Kobo Aura HD, Kobo Glo, Kobo Mini, Kobo Touch
  Motorola: Xoom, Xoom 2
  Onyx: Boox M92, Boox i62, Boox i62HD, Boox C65
 -Can also generate a default settings file if MangAI doesn't know the optimal settings for your particular ereader device
 -Any setting can be changed by the user to meet his/her needs
 -The latest device profiles are downloaded automatically
+Multithreading:
 -Mixed folder/image-level multithreading, allowing you to process multiple folders and images in parallel
 -Automatically determines the number of threads to use based on detected cpu cores and workload (you can also manually specify the number of threads)
+Other operations:
 -Organize
  -Organizes manga collection by interpreting volume-chapter info but does not process images
  -Especially helpful for users who want their scanlation archives automatically unpacked and organized but don't need them processed since they read on a computer rather than on an ereader
 -Full Organize
  -Same as Organize, but also interprets/sorts/renames the scans within the organized publications.
 -Analyze manga collection
  -Returns statistics such as # of file types, # scans, file space usage, etc.
 -Analyze manga folder
  -Returns statistics such as width, height, and dpi of each image in the folder
 -Create MangAI batch file (CLI only)
  -Creates a script to process your entire manga collection using MangAI
+Job management (GUI only):
 -Save a job so that you can run it again later (useful if you have added new chapters to a series)

Well, that's a list of the major features. For a full list of features you can read through the update history.

----------------------------------------------------------------------
-Processing an entire series of manga-

For processing a lot of manga at once, you can organize your manga like this:
  Series_Folder  <-- this is the folder you would specify as -source
    Blah_blah_v1    <-- this is a folder or .zip/.cbz/.rar/.cbr/.pdf holding the image scans for volume 1
    Blah_blah_c021    <-- this is a folder or .zip/.cbz/.rar/.cbr/.pdf holding the image scans for chapter 21
    ...
    Blah_blah_v999_c999_blah    <-- this is a folder or .zip/.cbz/.rar/.cbr/.pdf holding the image scans for volume 999 chapter 999

When you are ready to run the program, simply specify the root folder of the series as -source.
Here's an example:
  C:\Manga\SchoolRumble\
    v1_001.cbz
    vol01_chapter2.cbz
    ...
    School_Rumble_c283.cbz

java -jar mangai.jar -source C:\\Manga\\SchoolRumble\\ -title "School Rumble"

Note that this is an example of running the program on a Windows machine and I used \\ instead of \ in the command line. For Mac and Linux, the command would look more like:

java -jar mangai.jar -source /Users/john/Manga/SchoolRumble -title "School Rumble"

----------------------------------------------------------------------
-Setting up ImageMagick-

Windows users:
1) Download latest binary release: http://www.imagemagick.org/script/binary-releases.php#windows
You want the Q16 dll version, either 32-bit or 64-bit depending on your system (if you're not sure, go with 32-bit).
2) Double-click the downloaded .exe and follow the onscreen instructions.

You should also download and install Ghostscript if you want to use pdf files as input (not necessary if you only need to output pdf files).
1) Download latest Windows Ghostscript GPL Release here: http://www.ghostscript.com/download/gsdnld.html
Choose either the 32-bit or 64-bit version to match your version of ImageMagick.
2) Double-click the downloaded .exe and follow the onscreen instructions.

Linux users:
Use your favorite package manager (oh the joys of Linux).

Mac users:
1) Download and install MacPorts: http://www.macports.org/install.php
2) Open a terminal (Applications->Utilities->Terminal) and run:
   sudo port install ImageMagick

----------------------------------------------------------------------
-Setting up unrar/rar support-

Because rar is a proprietary format, MangAI cannot simply read or write a rar file itself. It relies on your system having a rar handler already installed.

For unrar support, make sure you have installed one of the following:
  For Linux/Unix: rar, unrar, unrar-nonfree
  For Windows: WinRAR (installed to [drive_letter]:\Program Files\[folder with "rar" in its name, such as WinRAR]\), 7zip
  For Mac: rar
For rar support, make sure you have installed one of the following:
  For Linux/Unix: rar
  For Windows: WinRAR (installed to [drive_letter]:\Program Files\[folder with "rar" in its name, such as WinRAR]\)
  For Mac: rar

You can download Rarlab's WinRAR (for Windows) or RAR (for Mac) from: http://www.rarlab.com/download.htm
Linux users can also get it from the above link, but it's easier just to go through your package manager of choice.

As WinRAR is not free, Windows users only interested in rar extraction may want to use 7zip instead. See -Setting up 7zip- for details.

Mac users:
Rarlab unrar/rar is a little different to install than standard Mac programs. If you are having trouble, follow these instructions:
1) Download RAR from the link above. It will come as a .tar.gz archive.
2) Extract the downloaded archive.
3) Open a terminal to the extracted folder (you can access terminal from Applications->Utilities->Terminal, then use cd to change to the extracted folder... for example, cd /Users/john/Downloads/rar/).
4) Run the following two commands to install unrar and rar (you may be prompted for your admin password):
   sudo install -c -o $USER unrar /opt/local/bin
   sudo install -c -o $USER rar /opt/local/bin
5) Run MangAI and create a new .properties file. MangAI should now auto-detect unrar and rar.

----------------------------------------------------------------------
-Setting up 7zip-

NOTE: 7zip is really only needed for Windows users who want to be able to read (but not write) rar files without buying WinRAR. Mac and Linux users should not need this.

The rar handlers for Mac and Linux are free, but WinRAR for Windows is not. 7zip is a free program that can extract rar files. 7zip, however, cannot archive files into rar format, it can only unrar. If you want to be able to archive into rar on Windows machines, you will still need WinRAR.

If you install a 7zip handler, you will also now be able to read standard 7zip formats such as .7z and .xz (although I've never come across a website that distributes scanlations in 7zip format, so you probably won't ever use this). Writing to 7zip formats is not included as I don't think any ereader can read those archives.

For 7zip support, make sure you have installed one of the following:
  For Linux/Unix: p7zip-full and p7zip-rar (both are needed)
  For Windows: 7zip (installed to [drive_letter]:\Program Files (x86)\[folder with "zip" in its name, such as 7-Zip]\)
  For Mac: p7zip

Windows users can download 7zip from: http://www.7-zip.org/download.html You want the standard version (i.e. the first download listed), NOT the command line version.
Linux users go through your package manager of choice.
Mac users will want to use MacPorts to install p7zip. After installing and configuring MacPorts (http://www.macports.org/install.php), open a terminal and run the following command:
  sudo macports install p7zip

----------------------------------------------------------------------
-Setting up pdftk-

You may have noticed MangAI slows to a crawl when creating a large pdf (>150 pages) and can possbily even fail.
This is likely because ImageMagick uses a crazy amount of system resources during pdf creation, and this demand seems to increase exponentially with pdf size.
Using pdftk in conjunction with ImageMagick will improve pdf creation success rate for large pdfs, as well as dramatically shorten processing time.
pdftk is freeware and is available across most major platforms.
You can download pdftk and get install instructions from: http://www.pdflabs.com/tools/pdftk-server/
For best results, use default settings when installing.

----------------------------------------------------------------------
-Setting up KindleGen-

In order to provide mobi output support, MangAI relies on Amazon's mobi packaging command line tool KindleGen: http://www.amazon.com/gp/feature.html?ie=UTF8&docId=1000765211

1) Visit Amazon's KindleGen webpage using the link above. If the link doesn't work, a quick Google search for KindleGen should get you to the download page.
2) Read KindleGen's term of use. If you accept the terms, download the version that matches your operating system.
3) Unpack the downloaded file.
4) Copy kindlegen (or kindlegen.exe if you are on Windows) to your MangAI directory.

MangAI should be able to automatically detect the KindleGen executable. If necessary, you may need to manually specify the path to the KindleGen executable yourself within the .properties settings. Make sure to also enable mobi output in the .properties settings.

----------------------------------------------------------------------
-Updating MangAI-

To update the program:
1) Download the latest version of MangAI from http://www.bitcake.com/projects/mangai/
2) Extract the archive.
3) Run the new jar.
4) It is recommended to create a new .properties settings file every time you update. Some settings may have changed or been added since the last program version. If you want to use your old settings file, you can specify the location of that file.
5) If you want to use GUI jobs created with a previous version of MangAI, you can copy the mangaigui_jobs.txt file from your old data directory into your new data directory.

To update the default device profiles:
Click Help -> Check for Update in the GUI or use -check_update argument in the CLI. If found, new device profiles will be downloaded automatically to your local MangAI directory.
You will need to restart the GUI to use newly downloaded device profiles.

The GUI will automatically check for updates once a week. You can also click Help -> Check for Update for an instant check. Follow bitcake on RSS or Twitter for the latest news.

----------------------------------------------------------------------
-Using the Graphical User Interface-

MangAI GUI provides a graphical user interface (GUI) to the command line interface (CLI) Java program MangAI. GUIs are usually easier and more intuitive to use than CLIs but require additional time to develop and resources to run.

Usage:
Run MangAI GUI by either double-clicking mangai_gui.jar (you may need to right-click->properties to give the jar execute rights), or you can open a terminal (Mac/Linux) or command prompt (Windows) to the MangAI folder and run:
  java -jar mangai_gui.jar

MangAI GUI is divided into several tabs: Run, Settings, Advanced, and Other Operations. Each tab has its own purpose and settings.

Run Tab:
Purpose: Specify mandatory settings, as well as run and monitor MangAI processing.
Settings/Buttons:
-source (Mandatory!)
  Location of source scans to process. See -Feature List- for a list of supported input formats, and -Processing an entire series of manga- to see how MangAI can process a whole series at once.
-Title (Mandatory!)
  Title of the manga, such as "School Rumble"
-output_folder
  Output location for processed scans. If not specified, MangAI will choose a default location for you.
-Run
  Process source using MangAI.
-Run Window
  Displays messages from MangAI during processing, such as what stage of processing it is in and time estimates until completion.
  The run window automatically scrolls down to show the latest messages. To read earlier messages without being auto scrolled to the latest message, a scroll lock is automatically enabled when you move your mouse over the run window.
-User Input
  If the Run Window shows MangAI is waiting for a response from the user, type your response here and press return/enter key.

Settings Tab:
Purpose: Specify global processing settings (i.e. settings you will use for all of your manga series).
Settings/Buttons:
-.properties file (Mandatory!)
  Stores global processing settings.
  A .properties file must exist before you can run MangAI.
  When creating a .properties file, you specify which ereader you want to process manga for (kindle3, ipad, etc.). MangAI then caters the default settings to those optimal for your device.
  Values are stored in key-value pairs in the format "key = value"
  You can change any value you want. The default settings for your device are not always optimal for your particular needs.
  When done making changes click the Apply button. To cancel recent changes click the Reload button.

Advanced Tab:
Important Note: These are optional settings. With the current version of MangAI you will most likely not want to mess with any of these settings (except perhaps -clear_resolutions).
Purpose: Specify optional settings.
Settings/Buttons:
-folder_interpretation
  Method used to interpret and sort source folders/archives/pdfs.
  Set to "mpm" if you want to interpret/sort using the Manga Publication Model (i.e. volume-chapter interpretation).
  Set to "string" if you want to sort using character-by-character filename comparisons.
  Recommended value is mpm.
  If not specified, the default value is mpm.
-scan_interpretation
  Method used to interpret and sort scans.
  Set to "msm" if you want to interpret/sort using the Manga Scan Model (i.e. volume-chapter-page interpretation).
  Set to "string" if you want to sort using character-by-character filename comparisons.
  Recommended value is msm.
  If not specified, the default value is msm.
-display_folder_interpretation
  Folder-level interpretation, as determined by @folder_interpretation, will be displayed on screen.
  Each source folder will be displayed on its own line in ascending order of sort, starting with the first folder, along with MangAI's interpretation of that folder.
  This can help you see if there are any problems with the @folder_interpretation method you chose.
-display_scan_ordering
  Scan ordering, as determined by @scan_interpretation, will be displayed on screen.
  Each scan filename will be displayed on its own line in ascending order of sort, starting with the first scan.
  This can help you see if there are any problems with the @scan_interpretation method you chose.
-bind_prefix
  This is placed after the manga title but before the binding number of bound manga.
  Recommend value is something like "Vol." or "Bind." so that the output will look like "School_Rumble_-_Vol.1"
  If not specified, the default value is "Vol.".
-auto_bind
  Enables auto binding of the specified size.
  For example: -auto_bind 5 will cause every sequential five image folders to be bound together.
  This value supersedes the one in the .properties file.
-clear_resolutions
  Clears saved conflict resolutions for source. Useful if you want to try different resolutions.

Other Operations Tab:
Purpose: Provide MangAI functions not directly related to processing scans.
Important Note: You can only have one Other Operation active at a time. While active no scan processing will be performed.
Settings/Buttons:
-Organize
  Organizes source to output_folder.
  See -Other Operations- section for more information on organizing your manga.
-Analyze Collection
  Analyzes collection at source.
  See -Other Operations- section of readme.txt for more information on analyzing your manga collection.
-Analyze Folder
  Analyzes source.
  See -Other Operations- section of readme.txt for more information on analyzing a manga folder.
-Create Debug Map
  Creates a debug map of the images within source for use by the developer.
  You should only use this feature when you have reported a problem to the developer and he has requested a debug map to help determine the cause.

In the File menu you can load and save jobs. A job consists of all the settings used for your current process. This allows you to easily reprocess a manga series without having to manually specify the same settings again.

In the Help menu you can find:
-A guide to using MangAI GUI
-Get License window to request a trial license or purchase a full license
-Check for updates
-About window

----------------------------------------------------------------------
-Using the Command Line Interface-

mangai.jar is a command line interface (CLI) Java program. This means you run it through your operating system's terminal (Mac/Linux) or command prompt (Windows).
If you prefer to use a graphical user interface (GUI), then use mangai_gui.jar instead. GUI-specific instructions are provided in Help->Contents.

Usage:
Open a terminal/command prompt in the folder containing mangai.jar. Then run:
  java -jar mangai.jar [mandatory_arguments] [other_options] [other_operation]
Example (Windows): java -jar mangai.jar -source "C:\\Manga\\School Rumble\\" -title "School Rumble"
Example (Mac/Linux): java -jar mangai.jar -source "/Users/john/Manga/School Rumble" -title "School Rumble"

Mandatory Arguments:
-source [path to source]
  The source containing all scans of the series.
  Source can be any of the supported MangAI input formats. Usually this is a folder.
  If the path contains spaces, then put double-quotes around the path; for example, "C:\\manga parent folder\\"
  Windows users: Use double backslashes \\ instead of single backslashes \ in the path.
-title "Manga Title"
  Title of the series, such as "School Rumble"

Optional Arguments:
-output_folder [path to output_folder]
  The output folder for processed manga. If not specified, default is an "OUT" folder created one directory above -source.
  If the path contains spaces, then put double-quotes around the path; for example, "C:\\manga output folder\\"
  Windows users: Use double backslashes \\ instead of single backslashes \ in the path.
-folder_interpretation [method]
  Method used to interpret and sort source folders/archives/pdfs.
  Set [method] to "mpm" if you want to interpret/sort using the Manga Publication Model (i.e. volume-chapter interpretation).
  Set [method] to "string" if you want to sort using character-by-character filename comparisons.
  Recommended value is mpm
  If not specified, the default value is mpm
-scan_interpretation [method]
  Method used to interpret and sort scans.
  Set [method] to "msm" if you want to interpret/sort using the Manga Scan Model (i.e. volume-chapter-page interpretation).
  Set [method] to "string" if you want to sort using character-by-character filename comparisons.
  Recommended value is msm
  If not specified, the default value is msm
-bind_prefix "[bind_prefix]"
  This is placed after the manga title but before the binding number of bound manga.
  Recommend value is something like "Vol." or "Bind." so that the output will look like "School_Rumble_-_Vol.1"
  If not specified, the default value is "Vol."
-properties [path to .properties file]
  Location of the .properties file. By default, MangAI assumes that the .properties file is in the same directory as the .jar.
  This option is only needed if you want to use a .properties file not located in the default location.
  If the path contains spaces, then put double-quotes around the path; for example, "C:\\a folder\\mangai.properties"
  Windows users: Use double backslashes \\ instead of single backslashes \ in the path.
-auto_bind [integer]
  Enables auto binding of the specified size.
  For example: -auto_bind 5 will cause every sequential five image folders to be bound together.
  This value supersedes the one in the .properties file.
-disable_binding
  Disables binding.
  This value supersedes all other binding settings.
-display_folder_interpretation
  Folder-level interpretation, as determined by @folder_interpretation, will be displayed on screen.
  Each source folder will be displayed on its own line in ascending order of sort, starting with the first folder, along with MangAI's interpretation of that folder.
  This can help you see if there are any problems with the @folder_interpretation method you chose.
-display_scan_ordering
  Scan ordering, as determined by @scan_interpretation, will be displayed on screen.
  Each scan filename will be displayed on its own line in ascending order of sort, starting with the first scan.
  This can help you see if there are any problems with the @scan_interpretation method you chose.
-clear_resolutions
  Clears saved conflict resolutions for source. Useful if you want to try different resolutions.

Other Operations: (these can only be run one at a time)
-check_update
  Checks the Internet for updates. Reports if a program update is available and provides a link to the download page.
  Also checks for and automatically downloads the latest device profiles.
-organize [path to organize_folder]
  MangAI will organize the contents of -source by volume and chapter and store the results in organize_folder, then the program exits (i.e. no image processing occurs).
  Organize recursively expands all archives and pdfs within -source, analyzes them for volume and chapter info, then renames/moves folders based on the analysis.
  MangAI usually organizes directly within -source at the beginning of processing. This separate function is useful if you just need your manga organized but not processed since you are reading on a computer rather than on an ereader.
  Note that organize_folder WILL BE DELETED when running this function in preparation for organization. DO NOT set organize_folder to a folder containing data (it will prompt you for deletion if it already exists).
-full_organize [path to organize_folder]
  Same as -organize, but also interprets/sorts/renames the scans within the organized publications.
-analyze_collection [path to root of manga collection]
  This will analyze all the files in your manga collection and give you statistics such as number of scans, amount of file space used, etc.
  Note that this can take a while to run depending on how large your manga collection is. The program will search and analyze all subdirectories within the specified root folder.
  If the path contains spaces, then put double-quotes around the path; for example, "C:\\manga collection\\"
  Windows users: Use double backslashes \\ instead of single backslashes \ in the path.
  See README.txt for more information on analyzing your manga collection.
-analyze_folder [path to manga folder]
  This will analyze all the image files in the given manga folder and give you image statistics such as width and height.
  [path to manga folder] can be an image folder, zip, rar, or pdf. If it is a zip, rar, or pdf, then the images within the file will be extracted to a temporary folder before being analyzed.
  The program will search recursively for all images under the path.
  If the path contains spaces, then put double-quotes around the path; for example, "C:\\manga parent folder\\" "C:\\manga output folder\\"
  Windows users: Use double backslashes \\ instead of single backslashes \ in the path.
  See README.txt for more information on analyzing a manga folder.
-create_batch [path to root input] [path to root output]
  Creates a batch script so that you can process all of the manga series in [path to root input] and output the processed manga to [path to root output].
  Note that this operation is not perfect, and you will have to read through the created batch script to make sure it determined all the values correctly.
  If the path contains spaces, then put double-quotes around the path; for example, "C:\\manga parent folder\\" "C:\\manga output folder\\"
  Windows users: Use double backslashes \\ instead of single backslashes \ in the path.
  See README.txt for more information on MangAI batch files.
-debug_create_map
  Creates a debug map of the images within -source for use by the developer.
  You should only use this feature when you have reported a problem to the developer and he has requested a debug map to help determine the cause.
-debug_recreate_from_map [path to debug map file]
  Recreates file structure from debug map.

Creating a default .properties file:
Usage: java -jar mangai.jar -create_properties [device_name]
Example: java -jar mangai-jar -create_properties kindle4
  The program will create a default .properties file for your particular ereader device.
  No manga will be processed when you use this option. This will create the default .properties file and then exit.
  Run MangAI without any arguments for a list of acceptable [device_name] values.

See mangai.properties for conversion settings.

See README.txt for manga organization requirements and other tips.

Requesting a trial license:
Usage: java -jar mangai.jar -request_trial "[your email address]"
Example: java -jar mangai.jar -request_trial "johndoe@gmail.com"
  The program will request a trial license from the licensing server (requires an Internet connection).
  The trial license will allow you to use MangAI for a set period of time, after which you may purchase a full license to continue using the program.
  Make sure to specify a valid email address as the trial license will be emailed to you.

See README.txt for more information on licensing.

----------------------------------------------------------------------
-Other Operations-

Other Operations are features embedded in MangAI that do not directly process scans, but instead add complementary functionality. These are especially useful for users that read digital manga on a computer/laptop rather than on an ereader and therefore may not require image processing.

Other Operations may be enabled in the command line or within the Other Operations tab of the GUI.

Organize
MangAI will organize the contents of source by volume and chapter and store the results in organize_folder, then the program exits (i.e. no image processing occurs).
The basic command to run this is: java -jar mangai.jar -source [path to source] -title [title of manga series] -organize [path to organize_folder]
Organize recursively expands all archives and pdfs within source, analyzes them for volume and chapter info, then renames/moves folders based on the analysis.
In a standard run, MangAI usually organizes directly within source. This separate function is useful if you want your scans to be easier to read on a computer/laptop, or if you want to see what organization looks like before processing.
The .properties file is required to run this operation since MangAI uses ImageMagick to expand pdfs to image folders and the ImageMagick install location is in the .properties file.
Because the rar format is proprietary, you must have a compatible rar handler in order to analyze rar (.rar/.cbr) files. See -Setting up unrar/rar support- for details.
Note that organize_folder WILL BE DELETED when running this function in preparation for organization. DO NOT set organize_folder to a folder containing data (it will prompt you for deletion if it already exists).

Full Organize
Same as Organize, but also interprets/sorts/renames the scans within the organized publications.
The basic command to run this is: java -jar mangai.jar -source [path to source] -title [title of manga series] -full_organize [path to organize_folder]
Scans will be renamed to incrementing 0-padded numbers (0000.jpg, 0001.jpg, etc.) in the order of scan interpretation sort.

Analyze Manga Collection
MangAI will search through a given root manga folder and return to you statistics about your collection (# of files of different types, # of scans, file space used, etc.).
The basic command to run this is: java -jar mangai.jar -analyze_collection [path to root of manga collection]
This operation can take a while to complete, depending on the size of your collection.
The .properties file is required to run this operation since MangAI uses ImageMagick to determine the number of pages in a pdf and the ImageMagick install location is in the .properties file.
Because the rar format is proprietary, you must have a compatible rar handler in order to analyze rar (.rar/.cbr) files. See -Setting up unrar/rar support- for details.

Analyze Manga Folder
MangAI will analyze all the image files in the given manga folder and give you image statistics such as width, height, and dpi.
The basic command to run this is: java -jar mangai.jar -analyze_folder [path to manga folder]
[path to manga folder] can be an image folder, zip, rar, or pdf. If it is a zip, rar, or pdf, then the images within the file will be extracted to a temporary folder before being analyzed.
The results will be displayed on the screen in a nicely formatted text table.
The .properties file is required to run this operation since MangAI uses ImageMagick to determine image statistics and the ImageMagick install location is in the .properties file.
Because the rar format is proprietary, you must have a compatible rar handler in order to analyze rar (.rar/.cbr) files. See -Setting up unrar/rar support- for details.

Create MangAI Batch File (CLI only)
Creates a batch file to process your entire manga collection with MangAI by just running the script (instead of having to manually type out a command line for each series).
The basic command to run this is: java -jar mangai.jar -create_batch [path to root input] [path to root output]
[path to root input] is the base folder of your unprocessed manga collection. For example, C:\\Manga\\, where C:\\Manga\\ contains all of your series folders like C:\\Manga\\SchoolRumble\\
[path to root output] is the base folder where you want MangAI to output the processed manga. Each processed series will get its own subfolder within this root output folder.
Note that the automatic process for creating a batch file is not perfect. Setting the -source value for each folder is easy, but determining the correct corresponding -title and -output_folder can be tricky. Therefore, after the batch file is created, read through it and double-check the command lines it produces. Correct any -title and -output_folder values as needed.
On a Windows machine it is recommended to edit the batch file with Wordpad.
Also note that when you first run the create batch file operation, a new file "lowercase_title.txt" will appear in the same folder as MangAI. This file contains a list of words that you don't want capitalized in series titles (like "the", "and", etc.), excluding the first word in a title of course. Read through this file and add or remove any words as needed.

----------------------------------------------------------------------
-Binding-

Binding allows you to combine multiple image folders into a single entity. The most common use is to combine chapters into volumes.

There are three ways to use binding:
1) Auto bind with auto volume detection
If you set @auto_bind to true and @auto_bind_size to auto, MangAI will attempt to automatically bind chapters belonging to the same volume. This requires MangAI to be able to interpret volume-chapter info from the source files.
The order of binding within a volume will be determined by chapter sorting in accordance with the Manga Publication Model.
You can also enable @auto_bind_fake_volumes so that sequential chapters without volume info will be bound (this is useful for the latest chapters not yet released in tankoban).

2) Auto bind with auto size
If you set @auto_bind to true and @auto_bind_size to an integer n, MangAI will automatically bind every n folders into a binding. The order of binding is determined alphabetically by folder name.

3) Bind file
To specify varying bind sizes, create a file called "bind.txt" and place it in source. Each line of bind.txt should be a single integer representing the size of each binding. For example if your bind.txt file contains:
10
8
9
Then the first binding will consist of the first 10 folders, the second binding the next 8 folders, and the third binding the next 9 folders. The order of binding is determined alphabetically be folder name.
MangAI will always check for a bind file and use it if found, superseding @auto_bind.

Most of the time you will want to use binding method 1 if you have volume-chapter info, or binding method 2 if you don't. Binding method 3 is still supported but is considered outdated. 

----------------------------------------------------------------------
-Specifying Other ImageMagick Ops-

Warning: FOR ADVANCED USERS ONLY! This can freeze, crash, or ruin the output if used incorrectly, and you will not receive any error messages.

The .properties setting @imagemagick_ops allows the user to specify additional operations for ImageMagick to perform on each image.
This allows the user to use ImageMagick convert options not already utilized by MangAI.
Only use image altering ops. Don't use anything that causes creation of another file.
See http://www.imagemagick.org/script/convert.php for list of options.
Example: -despeckle -level 0%,100%,1.2

For reference, MangAI makes three IM calls per image:
1) mogrify (with no options)
Helps fix bad image data so that Java has a better chance of being able to read the image natively.
2) trim or chop white rows/cols
Removes empty space so that the next IM call knows correct geometry.
3) convert with all options
All remaining ops are performed in a single convert command. This includes resizing, recoloring, sharpening, adding border(s), etc. A single convert helps save time vs an IM call per op.

It is during the last call that user defined ops are inserted among the other ops. 

----------------------------------------------------------------------
-Manga Intelligence Model-

MIM utilizes a combination of two concepts, the Manga Publication Model (MPM) and the Manga Scan Model (MSM). These concepts, used individually or in tandem, provide some interesting functionality not seen in programs lacking artificial intelligence.

By having knowledge of MPM, the user does not have to manually organize his/her manga beforehand, manually specify the order of volumes or chapters during runtime, or depend on alphabetical sort which is unreliable when dealing with scans from multiple sources or without conventional naming schemes. All the user has to do is point MangAI to source holding all the files of that series, and it will handle organizing all of your scans. You don't even have to unzip/unrar anything, MangAI will recursively go through all of the specified source, pick out all the chapters and volumes, and organize the output accordingly. And the only drawback... it is really hard to program :P

MSM provides a reliable, fully automatic way to sort the scans obtained from a manga publication. The user is not required to manually rearrange the ordering of pages when alphabetical sort fails. Later on this intelligence can be embedded within a manga reader and combined with MPM to allow for such features as dynamic table of contents, i.e. the ability for a reader to navigate by chapter within a volume when all chapter pages are located at the same folder level. Similar to MPM, the only drawback of MSM is that it is really hard to program.

Conforming to MIM means providing both MPM and MSM functionality. By facing this challenge, MangAI is better able to automatically interact with your digital manga collection. It's important to note though that, like all artificial intelligence, there is no perfect implementation of MPM or MSM; instead, it is up to the programmer(s) to write the best logic possible with the resources available, then improve it as needed over time. I believe that the combination of both MPM and MSM provides a digital manga experience not possible in applications lacking manga-centric intelligence.

----------------------------------------------------------------------
-Manga Publication Model-

The Manga Publication Model (MPM) describes the form in which a manga series is delivered to the reader. MPM consists of two levels of hierarchy, volume and chapter. Chapter is the basic unit of manga publication. Anytime somebody is reading manga, they are reading a particular chapter. Each chapter consists of one or more pages. Chapters are generally numbered sequentially (1, 2, 3, etc.) in order of publication. Some chapters may not have a number (such as special or bonus chapters), and some chapters may have lettered naming in addition to the number (such as 1a, 1b, 1c, etc.). For simplicity we'll denote volume 1 as v1 and chapter 1 as c1, though there are many other notations for volume and chapter such as vol and ch. Volume 1 chapter 1 can be denoted as v1_c1.

A volume, or "tankobon", is the highest level of the hierarchy. A volume consists of multiple chapters, and, similar to chapters, they are numbered sequentially (v1, v2, v3, etc.) in order of publication. Volumes are generally released after all of the chapters contained within have already been published separately; this means the latest chapters of a series may not have a volume number because that volume does not yet exist. Special/extra/bonus chapters may appear within a volume and are typically located after all standard chapters.

Ordering within MPM is predictable. Lower numbered volumes should appear before higher numbered volumes, and lower numbered chapters should appear before higher numbered chapters. A chapter with lettered naming (such as c1a) is sorted by number first, then alphabetically by letter; for example, c1 < c1a < c1b < c2.

----------------------------------------------------------------------
-Manga Scan Model-

The Manga Scan Model (MSM) describes what a particular digital image represents to the manga publication it was scanned from. Manga, like books, consists of sequential pages physically bound together using some form of spine (usually glue against hard paper). Each scan in MSM represents either one or two pages from the manga publication. If it represents two pages, then those pages are either the front and back of the binding or two sequential pages within the binding.

Besides the standard numbered pages that contain the main storyline of the series, there are special types of pages in MSM. The front and back covers represent the first and last pages respectively, usually with artwork and information about the pro/preceding pages but not actual storyline content. Insert pages can appear between the front cover and the first standard page. Extra pages, usually part of an extra/special chapter, appear between the last standard page and the back cover. If the scans were provided by a scan(lation) group, a credits page attributing who helped create the scans can appear for each chapter and/or volume. The location of a credits page can vary, but MangAI prefers to place them at the end of the chapter/volume.

Ordering of scans within MSM is similar to that within MPM, but MSM has to also consider page sorting in addition to volume and chapter. The basic ordering is:

    volume_front_cover
    volume_insert
        chapter_front_cover
        chapter_insert
        chapter_page
        chapter_extra_page
        chapter_back_cover
        chapter_credit
    volume_extra
    volume_back_cover
    volume_credit

Lettered naming for both chapters and pages is handled similar to MPM.

----------------------------------------------------------------------
-Update History-

New to version 1.15:
-AI improvements to MPM and MSM interpretation
Improved detection of string chapters (chapters with names instead of numbers, such as "Omake").
Improved detection of special pages within string and special chapters.
Better ordering of string and special chapters.
Added automatic conflict resolution of string chapters.
Find pages that don't a number in the filename but should be considered page #1.
Better handling of preceding chapter sort. This is used in rare instances to help sort pages by page number regardless of chapter numbering.
Various logic tweaks.
-Various fixes and improvements
No longer allows output_folder to be the parent (one folder up) of source in order to prevent potential conflicts.
Added a few non-English chapter delimiters: chapitre, cap’tulo, capitulo.

New to version 1.14:
-Bug fix: GUI Advanced -> scan_interpretation not working
Small bug, has been fixed.
-Updated helper programs install instructions
ImageMagick now requires installation of Ghostscript (free) if you want to use pdf files as input. Pdf as output is still fine without Ghostscript. Mac and Linux users should already have Ghostscript installed during installation of ImageMagick. Windows users need to download and install it separately.
The way pdftk is distributed has changed. I've updated the install instructions.
-New setting: @pdf_input_density
Integer value representing the dots per inch (DPI) of input pdf files.
High density value produces high resolutions when the pages of input pdf files are converted to images for further processing.
High density value, however, greatly increases processing time. Compared to density 72, density 150 takes about 3x longer to process, and 300 takes well over 10x longer!
If text comes out fuzzy, change to a different density and try again.
Acceptable values: 72, 96, 150, 300, 600, 1200
Recommended: 150
Pdf input density used to be fixed at 150. I kept 150 as the default, but you can try lower values to help speed up processing. Depending on the pdf, pages may look poor if you set the density too low compared to the original density they were scanned at.
-AI improvements to MPM and MSM interpretation
Better handling of special volume numbering.
Improved detection of undelineated page numbers (e.g. "c1-3" can be chapter 1 page 3).
Fixed rare crash during MSM scan sorting.
Improved handling when series title contains a number (e.g. "Eyeshield 21") that shouldn't be confused with volume, chapter, or page data.
Various logic tweaks.
-Various fixes and improvements
I came across a very rare case where a zip file would not extract properly if a zip entry was wrongfully detected as a file rather than a folder. This is now handled properly.
The program was not allowing trial license requests using emails with uppercase letters in the domain. This has been fixed.
I got a report of .properties settings file being created with some "null" values. I couldn't reproduce this bug on my test machines, but I modified code that hopefully fixes the problem.

New to version 1.13:
-External device profiles with automatic updating
Device profiles used to be embedded in MangAI. This meant that for new profiles to be added, a full program was required. While a program update usually takes at least a month to code, adding a new device profile only takes about five minutes.
Now device profiles are kept in a file external to the program. The latest device profiles can be automatically downloaded through the check for update feature.
-AI improvements to MPM interpretation
Better handling of special chapter numbering.
-Various fixes and improvements
MangAI makes sure output folder is not a subfolder of source. This prevents weird behavior during processing.
Lots of little improvements.

New to version 1.12b:
-Added more ereader .properties setting profiles
Settings profiles now available for:
  koboaura (Kobo Aura)

New to version 1.12a:
-Reduced mobi output file size
KindleGen, the free Amazon program used to generate mobi from epub, includes a copy of the input epub in its output mobi. For normal books this isn't a big deal, but for image-centric books like manga, doubling the file size is costly.
MangAI now automatically strips the input epub from the output mobi, reducing file size to be on par with epub.

New to version 1.12:
-Bug fix: Incorrect resize geometry
MangAI was calculating the resize geometry incorrectly, allowing some images to resize larger than the user settings (maximal area using @width or @height but should be using minimal).
Furthermore, adding a border to one of these images would instead remove image outskirts to shrink the image to the target size.
This has been fixed.
-New feature: Ignore folder
Simply add "MANGAI_IGNORE" to a folder's filename and its contents will not be processed. Useful if you don't want the latest scans (not in tankobon yet) or extra works (like special releases and fanart) to be processed with the main series.
-New setting: @check_free_space
If enabled, then MangAI will check disk free space before processing.
Estimation of required free space is based on user settings and source size.
If there is not enough free space, you are given a warning and asked if you would like to continue. This is useful since MangAI may quit unexpectedly if it runs out of space while processing.
-New setting: @jpeg_quality
Integer value between 1 and 100, inclusive, of percentage quality of output jpeg images.
100 is highest quality, while 1 is lowest quality. Lower quality should reduce file size but may also decrease readability (lossy compression).
Set to -1 for default quality which is determined per image based on a quick analysis. Average default quality is around 92.
Acceptable values: -1 (for default quality), an integer between 1 and 100 inclusive
-New setting: @sharpen
Sharpening can help increase readability of blurry text.
Integer value between 1 and 100, inclusive, of percentage to sharpen images.
100 is the most sharpening, while 1 is lowest sharpening.
Acceptable values: -1 (to disable), an integer between 1 and 100 to sharpen
New setting: @imagemagick_ops
Warning: FOR ADVANCED USERS ONLY! This can freeze, crash, or ruin the output if used incorrectly, and you will not receive any error messages.
Additional operations for ImageMagick to perform on each image.
This allows the user to use ImageMagick convert options not already utilized by MangAI.
Example: -despeckle -level 0%,100%,1.2
See -Specifying Other ImageMagick Ops- in Help->Contents or README.txt for details.
-Added more ereader .properties setting profiles
Settings profiles now available for:
  koboaurahd (Kobo Aura HD)
  koboglo (Kobo Glo)
  kobomini (Kobo Mini)
  kobotouch (Kobo Touch)
-AI improvements to MPM interpretation
When there are multiple chapter delimiters in a filename, start with best one (i.e. prefer ch to c).
Better handling of special chapter numbering.
-GUI improvements
Double-click to load job from table.
GUI remembers last selected device when creating .properties settings file.
Buttons for Apply and Reload settings should now be easier to read.
-Various fixes and improvements
Fixed description of setting @adjust_contrast. Negative value increases contrast, while positive value decreases contrast.
Fixed warning of unrar program not detected even though 7zip was found.

New to version 1.11:
-New settings: @upscale and @downscale
If @upscale is disabled, then images smaller than the specified output size will not be resized.
If @downscale is disabled, then images larger than the specified output size will not be resized.
The default behavior of MangAI will continue to be to upscale small images to better fill the screen and to downscale large images to save device memory.
-Added more ereader .properties settings profiles
Settings profiles now available for:
  kobovox (Kobo Vox)
  obooxm92 (Onyx Boox M92)
-Various fixes and improvements
Removed setting @max_dpi since dpi is not relevant to digital reading.

New to version 1.10:
-Mobi output support
MangAI now offers creation of .mobi files.
This output format was added as per the request of a MangAI user who discovered that mobi is the best comic format for Kindle Paperwhite.
As Amazon owns Mobipocket, there's a good chance that other Kindle devices will like mobi best as well. For now, mobi output is only enabled by default for the Kindle Paperwhite, but you can enable it for any device in .properties settings.
Mobi output requires Amazon's free KindleGen tool. See -Setting up KindleGen- in Help->Contents or README.txt for details.
-Various fixes and improvements
Automatically update the .properties settings file when new helper programs are discovered.

New to version 1.09:
-Bug fix: Missing code during final MangAI packaging
One of the final steps of packaging MangAI is code shrinking to remove unused and unnecessary code. Unfortunately, the shrinker I was using was removing code that appeared to be useless but was important.
I'm not sure which algorithms were affected by this. At the very least, string chapter detection (e.g. v1cb1) was not working as well as it could.
This has been fixed.
-AI improvements to MPM interpretation
Better automatic conflict resolution, especially for conflicts between lettered chapters.
-Added more ereader .properties settings profiles
Settings profiles now available for:
  phone (mobile phone)
Some of the latest mobile phones can make for decent manga readers. The experience is nothing like reading on a larger tablet, but still nice if you can't always carry your tablet with you.
The phone settings profile leaves scans at their original resolution for improved pinch zooming. White space is chopped to fit more content on screen.
If on a phone, I recommend reading in landscape (i.e. hold phone sideways, the full width of the scan fills the height of the phone, scroll up/down to read).

New to version 1.08:
-Improved string sort for filenames containing numbers
This is best explained with an example. Considering the following images:
ch123_1.jpg, chapter123_2.jpg, ..., chapter123_10.jpg, chapter123_11.jpg
With standard character-by-character string comparisons, you would end up with sorting like:
chapter123_1.jpg, chapter123_10.jpg, chapter123_11.jpg, chapter123_2.jpg, ...
because the "1" in "10" and "11" is sorted before "2".
MangAI is now better able to string sort filenames containing numbers.
This update applies to both AI and string based publication and scan sorting methods.
-Added more ereader .properties settings profiles
Settings profiles now available for:
  ipad4 (Apple iPad 4th generation)
  ipadmini (Apple iPad mini)
  kindlefirehd8.9 (Amazon Kindle Fire HD 8.9")
  nexus10 (Google Nexus 10)
-Various fixes and improvements
Fixed crash when using a settings profile created with an earlier version of MangAI. The crash was caused by trying to load a setting that wasn't available in earlier versions. MangAI will now use default values for missing settings.

New to version 1.07:
-Renamed "parent_folder" to "source"
This change has been a long time coming. Since your input file is not necessarily a folder (can also be an archive or pdf), the name "source" makes more sense.
-Added 7zip handler (mainly for unrar on Windows)
The rar handlers for Mac and Linux are free, but WinRAR for Windows is not. 7zip support has been added to MangAI since the program is free and can extract rar files. Note that 7zip cannot archive files into rar format; it can only unrar. If you want to be able to archive into rar on Windows machines, you will still need WinRAR.
Installing a 7zip handler will also enable MangAI to read standard 7zip formats such as .7z and .xz. Writing to standard 7zip formats is not included as I am not aware of any ereader that can read those archives.
See -Setting up 7zip- for details on installing 7zip on your computer.
-Added more ereader .properties settings profiles
Settings profiles now available for:
  kindle5 (Amazon Kindle 5)
  kindlefirehd7 (Amazon Kindle Fire HD 7")
  kindlepaperwhite (Amazon Kindle Paperwhite)
  nexus7 (Google Nexus 7)
  prs-t2 (Sony PRS-T2)
Note that the profile for Kindle Fire HD has resizing disable by default. This improves detail when zoomed but comes at the cost of increased file sizes. If you instead want to match the screen resolution of the Kindle Fire HD, set width to "800" and height to "1280".
The profile for Google Nexus 7, on the other hand, does resize by default to match screen resolution. If space isn't an issue for you (like if you have the 16GB model), feel free to disable resizing by setting width and height to "-1".
-Various fixes and improvements
GUI remembers last selected source to make it quicker to navigate subsequent source selection.
Miscellaneous tweaks.

New to version 1.06:
-AI improvements to MPM and MSM interpretation
Better able to detect unspecified spans (1112 meaning 11-12).
Better able to detect when a hyphen does not indicate a span (50-200 is two separate numbers 50 and 200 instead of span 50 through 200).
Improved ability of MPM to find non-delineated chapter spans (foo_v3_15-20 is likely volume 3, chapters 15-20).
MSM interpretation is automatically disabled for folders with low confidence. This usually happens for small folders of scanlator notes or fanart. In these cases, it is better to sort using basic filename string comparison.
Improved MSM handling of special scan overspecification (p3_notes.jpeg is probably better placed between p3.jpeg and p4.jpeg instead of near the end of the volume/chapter since it likely explains something just read in p3.jpeg).
Various logic tweaks.
-Overhauled epub creation code
Tweaked the basic structure of created epub files so that they (hopefully) display better across all devices.

New to version 1.05:
-Bug fix: GUI forgets settings file during auto update check
The GUI Settings tab would sometimes show mangaigui.properties instead of mangai.properties.
It took me a while to track down this bug since it occurs during automatic update checking which is only once a week :P
This has been fixed.
-AI improvements to MPM and MSM interpretation
MSM is better able to detect chapter spans.
Various logic tweaks.
-Added ereader .properties settings profile for iPad 3
All of the currently existing iPad's use the same settings anyway, but I like having the user to be able to pick out their exact device from the list.
And yes, I know the latest iPad is not named iPad 3, but simply calling it iPad could make it be confused with the 1st gen.
-GUI improvements
Added links in the Contents panel for the MangAI FAQ, Manga Intelligence Model, and Contact web pages.
-Various fixes and improvements
Memory optimizations for image reads.
Search for ImageMagick directory at runtime if .properties file value is missing or invalid.
Miscellaneous little things that I can't remember.

New to version 1.04:
-Bug fix: Chop cancel not calculating properly
One missing character of code was causing chop cancel to calculate incorrectly, potentially leading to situations where chopping should have been canceled but continued anyway.
This has been fixed.
-Improved @auto_trim/@chop_white_rows/@chop_white_cols when combined with @auto_rotate
In very rare instances, white space trimming/chopping could remove enough area to cause a scan to change orientation (i.e. a portrait scan could become landscape, or vice versa).
In this scenario, you probably don't want the image to be rotated since the original orientation is the intended way to read that scan.
This has been improved by canceling trimming/chopping if it would lead to an orientation change and rotation.
-Added more ereader .properties settings profiles
Settings profiles now available for:
  kindle4 (Amazon Kindle 4)
  kindletouch (Amazon Kindle Touch)
  nooktouch (Barnes & Noble Nook Touch)
I also renamed some of the existing profiles to remove ambiguity. For example, "k1" may have been mistaken for Kobo 1, so I renamed it to "kindle1".
Contact me if you would like a settings profile added for your device.

New to version 1.03:
-Bug fix: Potential freeze during Manga Scan Model scan analysis
Program could freeze during MSM interpretation due to logic bug. This has been fixed.
-AI improvements to MPM and MSM interpretation
Small improvement to Manga Publication Model interpretation to better handle volumes with only one chapter available but that lacks chapter info (a rare case but still good to handle properly).
Several improvements to Manga Scan Model interpretation, including better sorting of mixed volume/chapter specification levels, improved ability to find cover pages that don't include the keyword "cover" in the filename, and smarter handling of special page overspecification.

New to version 1.02a:
-Bug fix: Potential freeze during Manga Scan Model scan analysis
Bug was introduced in v1.02 and allowed for a potential infinite loop during MSM interpretation.
This has been fixed.
-Added more ereader .properties settings profiles
Settings profiles now available for:
  pc (personal computer or laptop)
  eeepad (Asus Eee Pad Transformer and Transformer Prime)
  touchpad (HP TouchPad)
  xoom2 (Motorola Xoom 2)
Contact me if you would like a settings profile added for your device.
-Better default settings for iPad 1 and 2
Disabled pdf output since cbz is better to read and faster to produce.

New to version 1.02:
-AI improvements to MSM interpretation
Better front and back cover page detection for scans that don't include "cover" in the filename.
Better handling overspecification of special pages. Overspecification refers to not always treating pages like covers and credits as special since, under certain circumstances, they are better sorted using normal page ordering rules.
Improved MSM sorting, particularly for chapter ordering and special pages.
Better ignoring of version numbers. "v2" meaning "version 2" rather than "volume 2" is a real pita :P
Various other logic improvements.
-Better default settings for Kobo 1 and 2
Thanks to user feedback, the default profiles for kobo1 and kobo2 have been improved. Cbz output is in, epub and pdf are out, and images smaller than the screen size get a white border added to help with centering.
-Auto check for software update
GUI will now automatically check for a new MangAI version once a week. You can still manually check for an update at any time by clicking Help -> Check for Update.

New to version 1.01:
-Bug fix: Potential freeze during Manga Scan Model scan analysis
There was a possibility to enter an infinite loop of deciding between different scan interpretations, causing the program to freeze.
This has been fixed.
-New setting: @output_prefix
This prefix is placed before every output publication filename.
Leave blank if you don't want to use a prefix.
Supported shorthand:
  [title] - manga series title specified by user at runtime
Examples:
  If set to "[title]~", output files will look something like "School_Rumble~School_Rumble_-_v1_c1-10.cbz".
Setting @comic_zeal_naming has been removed. If you want to use that same naming scheme, set @output_prefix to "[title]~".
-New Other Operation: Full Organize
Same as Organize, but also interprets/sorts/renames the scans within the organized publications.
Scans will be renamed to incrementing 0-padded numbers (000000.jpg, 000001.jpg, etc.) in order of scan interpretation sort.
-Overhauled epub creation code
The old code created invalid epub files. I looked up the latest standards and rewrote the engine.
Tested output on my iPad's iBooks app (which is my only epub reader) and it seems to be correct now.
I still don't like epubs for manga scans since most epub reader software is designed for displaying text rather than full page images; however, I want to keep epub as an output option in case it is the only supported format on your ereader.
-Added more ereader .properties settings profiles
Settings profiles now available for:
  nookc (Nook Color)
  nookt (Nook Tablet)
  kf (Kindle Fire)
  ipad2 (Apple iPad 2)
  galaxy10 (Samsung Galaxy Tab 10.1)
  galaxy7.7 (Samsung Galaxy Tab 7.7)
  galaxy7 (Samsung Galaxy Tab 7)
  prs-t1 (Sony PRS-T1)
  sony-s (Sony Tablet S)
  kobov (Kobo Vox)
  xoom (Motorola Xoom)
Please contact me if you would like a profile for your ereader added to MangAI.
-AI improvements to MPM and MSM interpretation
Too many things to list. The latest version of MangAI is always the best so be sure to stay updated.
-Various fixes and improvements
Reorganized .properties settings file to be easier to work with.
Found some small bugs and squashed them flat.

New to version 1.00b:
-Bug fix: Invalid collection of computer data for trial licensing
As trial licenses are tied to both your email address and your computer, gathering the correct computer information is important.
I accidentally left open the possibility for invalid computer information to be gathered. This could cause multiple computers to map to the same identification, and, in turn, new users could be denied a trial license.
This has been fixed.
-Check for Update feature added to the GUI
Click Help->Check for Update to see if a new version of MangAI is available.

New to version 1.00a:
-Bug fix: "There was a problem communicating with the licensing server."
When trying to request or activate a license, you would get the above error message. This has been fixed.

----------------------------------------------------------------------
-License-

MangAI is copyright (c) 2011 - 2015 Andrew Mantel.

See the included License_Agreement.txt for license details.