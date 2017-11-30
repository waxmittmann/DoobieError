package templatey

import scalaz._, Scalaz._
import scalaz.concurrent.Task

import doobie.imports._
import scalaz.concurrent.Task.taskInstance


object Main {

  def main(args: Array[String]): Unit = {
    val db: Transactor[Task] = ???

    val cio: ConnectionIO[Int] = 5.point[ConnectionIO]

    // This works because the apply is on NaturalTransformation rather than calling Transactor's trans
    val taskA: Task[Int] = db.trans.apply(cio)

    // This doesn't work because db.trans requires an implicit monad, and passing the cio like this puts it into the
    // implicit monad slot
    //val taskB: Task[Int] = db.trans(cio)

    // Explicitly passing in the monad works
    val taskB: Task[Int] = db.trans(scalaz.concurrent.Task.taskInstance)(cio)

    // This notation doesn't work
    //val taskC: Task[Int] = (db.trans)(cio)

    // Storing it and then using it also works
    val trans: ~>[ConnectionIO, Task] = db.trans
    val taskC: Task[Int] = trans(cio)

    // Ok, I think THIS is how we're meant to use it now
    val x: Task[Int] = cio.transact(db)

  }

}
