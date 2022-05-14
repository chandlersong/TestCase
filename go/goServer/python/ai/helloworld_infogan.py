"""
Created on Thu Aug 22 13:44:44 2019

@author: zhang
"""
import os

import torch
import torch.nn as nn
from torch.autograd import Variable
from torchvision.utils import save_image
import numpy as np
from loguru import logger
import random

bs = 100
idxmap = {1: 0,
          7: 1,
          5: 2,
          4: 3,
          9: 4,
          3: 5,
          6: 6,
          8: 7,
          0: 8,
          2: 9}


class G(nn.Module):
    def __init__(self):
        super(G, self).__init__()
        self.main = nn.Sequential(
            nn.ConvTranspose2d(74, 1024, 1, 1, bias=False),
            nn.BatchNorm2d(1024),
            nn.ReLU(True),
            nn.ConvTranspose2d(1024, 128, 7, 1, bias=False),
            nn.BatchNorm2d(128),
            nn.ReLU(True),
            nn.ConvTranspose2d(128, 64, 4, 2, 1, bias=False),
            nn.BatchNorm2d(64),
            nn.ReLU(True),
            nn.ConvTranspose2d(64, 1, 4, 2, 1, bias=False),
            nn.Sigmoid()
        )

    def forward(self, x):
        x = self.main(x)
        return x


def _Noise_sample(n):
    dis_c = torch.FloatTensor(bs, 10)
    con_c = torch.FloatTensor(bs, 2)
    noise = torch.FloatTensor(bs, 62)
    c = np.linspace(-1, 1, 10).reshape(1, -1)
    c = np.repeat(c, 10, 0).reshape(-1, 1)
    c1 = np.hstack([c, np.zeros_like(c)])
    # idx = np.random.choice(10,10,replace=False).repeat(10)
    # fix number
    idx = np.arange(10).repeat(10)
    one_hot = np.zeros((100, 10))
    one_hot[range(100), idx] = 1
    fix_noise = torch.Tensor(100, 62).uniform_(-1, 1)
    con_c = Variable(con_c)
    con_c.data.resize_(bs, 2)
    noise = Variable(noise)
    noise.data.resize_(bs, 62)
    noise.data.copy_(fix_noise)
    dis_c.data.copy_(torch.Tensor(one_hot))
    con_c.data.copy_(torch.from_numpy(c1))
    z = torch.cat([noise, dis_c, con_c], 1).view(-1, 74, 1, 1)
    real_idx = idxmap[n]
    z = z[real_idx * 10:real_idx * 10 + 10]
    return z


class InfoGanService:
    def __init__(self) -> None:
        root = os.path.abspath(os.path.dirname(__file__))
        logger.info(f"root path is {root}")
        net = G()
        net.eval()
        self.use_cuda = torch.cuda.is_available()
        if self.use_cuda:
            logger.info('use cuda')
            net.cuda()
        else:
            logger.info('use cpu')
        model_path = f'{root}/model.pth'
        is_gpu = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
        logger.info('model path: {}'.format(model_path))
        ckpt = torch.load(model_path, map_location=is_gpu)
        net.load_state_dict(ckpt['model'])
        self.net = net
        self.export_path = root + "/" + "infoganResult"
        logger.info(f"export path is {self.export_path}")

    def save(self, number: int) -> int:
        z = _Noise_sample(number)
        if self.use_cuda:
            z = z.cuda()

        res = self.net(z)
        res_text = f"{random.randint(1,9999999999)}.png"
        save_image(res.data, f"{self.export_path}/{res_text}", nrow=10)
        return res_text
