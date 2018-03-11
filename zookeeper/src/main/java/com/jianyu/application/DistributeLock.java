package com.jianyu.application;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DistributeLock {
    private static final String ROOT_LOCKS = "/LOCKS"; //根节点
    private ZooKeeper zooKeeper;
    private int sessionTimeout;

    private String lockID;

    private final static byte[] data={1,2};

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    //构造方法
    public DistributeLock() throws IOException, InterruptedException {
        this.zooKeeper = ZookeeperClient.getInstance();
        this.sessionTimeout = ZookeeperClient.getSessionTimeout();
    }

    public boolean lock(){
        try {
            lockID = zooKeeper.create(ROOT_LOCKS+"/",data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName()+"-->成功创建了lock节点["+lockID+"]，开始竞争锁");

            List<String> childrenNodes = zooKeeper.getChildren(ROOT_LOCKS,true);

            SortedSet<String> sortedSet = new TreeSet<String>();

            for (String children:childrenNodes) {
                sortedSet.add(ROOT_LOCKS+"/"+children);
                System.out.println("sortedSet:"+sortedSet);
            }

            String first = sortedSet.first();
            if (lockID.equals(first)){
                System.out.println(Thread.currentThread().getName()+"-->成功获取锁，lock节点为:["+lockID+"]");
            }

            SortedSet<String> lessThanLockid = sortedSet.headSet(lockID); // 当前比lockId小的所有节点
            // 如果没有比当前节点小的，那么当前节点就是最小的，创建了最小节点的这个用户就获得锁
            if(!lessThanLockid.isEmpty()){
                String prevLockId = lessThanLockid.last();
                System.out.println("prevLockId:"+prevLockId);
                zooKeeper.exists(prevLockId,new LockWatcher(countDownLatch));
                System.out.println("countDownLatch:"+countDownLatch);
                // 如果会话超时，节点被删除（释放了）
                countDownLatch.await(sessionTimeout, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName()+" 成功获取锁：["+lockID+"]");
            }
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  false;
    }

    public boolean unlock(){
        System.out.println(Thread.currentThread().getName()+"->开始释放锁:["+lockID+"]");
        try {
            zooKeeper.delete(lockID,-1);
            System.out.println("节点["+lockID+"]成功被删除");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        final CountDownLatch latch=new CountDownLatch(10);
        final Random random=new Random();
        for (int i=0;i<10;i++) {
            new Thread(()->{
                DistributeLock lock=null;
                try{
                    lock = new DistributeLock();
                    latch.countDown();
                    latch.await();
                    lock.lock();
                    //Thread.sleep(random.nextInt(5000));
                    Thread.sleep(2000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(lock!=null){
                        lock.unlock();
                    }
                }
            }

            ).start();
        }

    }
}
