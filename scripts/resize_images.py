from PIL import Image
import os

path = r"C:\Users\stefcho\GitHub\taxe-game-3\taxe\core\assets\trains"

os.chdir(path)

for file in os.listdir(path):
	if ".png" not in file:
		continue

	with Image.open(file) as im:
		im = im.resize((64,64))
		im.save(path + "/cursor/" + file)

print "Images Resized!"
