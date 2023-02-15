/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;

/**
 *
 * @author hcadavid
 */
public class Consumer extends Thread{

    private Queue<Integer> queue;

    private int limit;


    public Consumer(Queue<Integer> queue){
        this.queue=queue;
    }

    public Consumer(Queue<Integer> queue, int limit){
        this.queue=queue;
        this.limit = limit;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                if (queue.size() == limit) {
                    queue.notify();
                    int elem = queue.poll();
                    System.out.println("Consumer consumes " + elem);
                }
                else {
                    try {
                        if(queue.size() == 0){
                            queue.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}