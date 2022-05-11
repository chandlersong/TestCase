import os

import torch.nn
from torch import nn
from torchvision import transforms
from loguru import logger
import cv2
import torch.nn.functional as F

device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")


class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.conv1 = nn.Conv2d(1, 32, 3, 1)
        self.conv2 = nn.Conv2d(32, 64, 3, 1)
        self.dropout1 = nn.Dropout(0.25)
        self.dropout2 = nn.Dropout(0.5)
        self.fc1 = nn.Linear(9216, 128)
        self.fc2 = nn.Linear(128, 10)

    def forward(self, x):
        x = self.conv1(x)
        x = F.relu(x)
        x = self.conv2(x)
        x = F.relu(x)
        x = F.max_pool2d(x, 2)
        x = self.dropout1(x)
        x = torch.flatten(x, 1)
        x = self.fc1(x)
        x = F.relu(x)
        x = self.dropout2(x)
        x = self.fc2(x)
        output = F.log_softmax(x, dim=1)
        return output


class MnistService:
    def __init__(self) -> None:
        root = os.path.abspath(os.path.dirname(__file__))
        logger.info(f"root path is {root}")
        model = Net()
        model.load_state_dict(torch.load(f'{root}/mnist_cnn.pt'))
        gpu = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
        logger.info('use gpu inference' if gpu.type != 'cpu' else 'use cpu inference')
        self.model = model.to(gpu)
        self.transform = transforms.Compose([
            transforms.ToTensor(),
            transforms.Normalize((0.1307,), (0.3081,))
        ])
        self.root = root

    def predict(self, file_id: str) -> int:
        img = cv2.imread(f"{self.root}/numberPng/{file_id}.png", 0)
        img = self.transform(img).unsqueeze(0)
        with torch.no_grad():
            output = self.model(img)
            label = torch.argmax(output, 1)
            print('pred_label: {}'.format(label.item()))
            return label
