抽帧效果不好，这里仅记录日志：
```bash
➜  gif git:(master) ✗ pip install pillow
DEPRECATION: Python 2.7 will reach the end of its life on January 1st, 2020. Please upgrade your Python as Python 2.7 won't be maintained after that date. A future version of pip will drop support for Python 2.7.
Collecting pillow
  Downloading https://files.pythonhosted.org/packages/d1/6a/41719faa7421602a85941867059f53787ac40c85c8fe9e6bb48809e3246e/Pillow-6.2.2-cp27-cp27m-macosx_10_6_intel.whl (3.9MB)
     |████████████████████████████████| 3.9MB 4.3MB/s 
Installing collected packages: pillow
ERROR: Could not install packages due to an EnvironmentError: [Errno 13] Permission denied: '/Library/Python/2.7/site-packages/PIL'
Consider using the `--user` option or check the permissions.

➜  gif git:(master) ✗ sudo pip install pillow
Password:
DEPRECATION: Python 2.7 will reach the end of its life on January 1st, 2020. Please upgrade your Python as Python 2.7 won't be maintained after that date. A future version of pip will drop support for Python 2.7.
WARNING: The directory '/Users/dongguabai/Library/Caches/pip/http' or its parent directory is not owned by the current user and the cache has been disabled. Please check the permissions and owner of that directory. If executing pip with sudo, you may want sudo's -H flag.
WARNING: The directory '/Users/dongguabai/Library/Caches/pip' or its parent directory is not owned by the current user and caching wheels has been disabled. check the permissions and owner of that directory. If executing pip with sudo, you may want sudo's -H flag.
Collecting pillow
  Downloading https://files.pythonhosted.org/packages/d1/6a/41719faa7421602a85941867059f53787ac40c85c8fe9e6bb48809e3246e/Pillow-6.2.2-cp27-cp27m-macosx_10_6_intel.whl (3.9MB)
     |████████████████████████████████| 3.9MB 1.2MB/s 
Installing collected packages: pillow
Successfully installed pillow-6.2.2
➜  gif git:(master) ✗ python reduce_gif.py path_to_your_gif.gif 2
Traceback (most recent call last):
  File "reduce_gif.py", line 2, in <module>
    from PIL import Image
ModuleNotFoundError: No module named 'PIL'
➜  gif git:(master) ✗ python reduce_gif.py path_to_your_gif.gif 2
Traceback (most recent call last):
  File "reduce_gif.py", line 2, in <module>
    from PIL import Image
ModuleNotFoundError: No module named 'PIL'
➜  gif git:(master) ✗ pip show pillow
DEPRECATION: Python 2.7 will reach the end of its life on January 1st, 2020. Please upgrade your Python as Python 2.7 won't be maintained after that date. A future version of pip will drop support for Python 2.7.
Name: Pillow
Version: 6.2.2
Summary: Python Imaging Library (Fork)
Home-page: http://python-pillow.org
Author: Alex Clark (PIL Fork Author)
Author-email: aclark@python-pillow.org
License: HPND
Location: /Library/Python/2.7/site-packages
Requires: 
Required-by: 
➜  gif git:(master) ✗ python2 reduce_gif.py path_to_your_gif.gif 2
  File "reduce_gif.py", line 7
SyntaxError: Non-ASCII character '\xe6' in file reduce_gif.py on line 7, but no encoding declared; see http://python.org/dev/peps/pep-0263/ for details
➜  gif git:(master) ✗ 

```


