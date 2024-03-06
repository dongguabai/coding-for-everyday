# -*- coding: utf-8 -*-
import sys
from PIL import Image
import os
import time

def reduce_gif_frames(input_path, frame_skip):
    # 打开GIF图片
    img = Image.open(input_path)

    # 创建新的帧列表
    new_frames = []
    i = 0
    try:
        while True:
            if i % frame_skip == 0:
                new_frames.append(img.copy())
            i += 1
            img.seek(i)
    except EOFError:
        pass

    # 获取原文件名和目录
    directory, filename = os.path.split(input_path)
    filename, _ = os.path.splitext(filename)

    # 创建新的文件名
    new_filename = "{}_{}.gif".format(filename, int(time.time()))
    output_path = os.path.join(directory, new_filename)

    # 保存新的GIF图片
    new_frames[0].save(output_path, save_all=True, append_images=new_frames[1:], loop=0)

    return output_path

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python reduce_gif.py <input_path> <frame_skip>")
        sys.exit(1)

    input_path = sys.argv[1]
    frame_skip = int(sys.argv[2])

    new_gif_path = reduce_gif_frames(input_path, frame_skip)
    print("New GIF saved at: {}".format(new_gif_path))