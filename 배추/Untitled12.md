```python
#배추값 계산하기 
import tensorflow.compat.v1 as tf
tf.disable_v2_behavior() 

import numpy as np
```

    WARNING:tensorflow:From C:\Users\VSC15\anaconda3\lib\site-packages\tensorflow\python\compat\v2_compat.py:107: disable_resource_variables (from tensorflow.python.ops.variable_scope) is deprecated and will be removed in a future version.
    Instructions for updating:
    non-resource variables are not supported in the long term
    


```python
X = tf.placeholder(tf.float32, shape=[None, 4])

Y = tf.placeholder(tf.float32, shape=[None, 1])



W = tf.Variable(tf.random_normal([4, 1]), name="weight")

b = tf.Variable(tf.random_normal([1]), name="bias")

```


```python
hypothesis = tf.matmul(X,W) +b # 가설값
```


```python
# 초기화
saver = tf.train.Saver()
model = tf.global_variables_initializer() 
```


```python
# 입력값
avg_temp =float(input('평균온도:'))
min_temp =float(input('최저온도:'))
max_temp =float(input('최고온도:'))
rain_fall=float(input('강수량:'))
```

    평균온도:15.5
    최저온도:3.5
    최고온도:20.5
    강수량:5
    


```python
with tf.Session() as sess:
    sess.run(model)
    
    # 저장된 학습 모델을 파일로부터 불러옵니다.

    save_path = "./saved.cpkt"
    saver.restore(sess, save_path)
    
    #2차원베열~입렵값을 이용해 배열을 만듬 
    data = ((avg_temp, min_temp, max_temp, rain_fall), )
    arr = np.array(data, dtype=np.float32)
    
    
    # 예측을 수행한 뒤에 그 결과를 출력합니다.
    x_data = arr[0:4]

    dict = sess.run(hypothesis, feed_dict={X: x_data})

    print(dict[0])
```

    INFO:tensorflow:Restoring parameters from ./saved.cpkt
    [-3.214855]
    


```python

```
