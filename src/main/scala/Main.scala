import javax.swing.{JDialog, JFrame, UIManager}

import swing._
import event._


class LaunchGame {
  def showNewWindow() = {
    val frame: Frame = new GameFrame()
  }

  def createButtonPane(f: Frame): FlowPanel = {
    val button = new Button("Launch Game")
    f.listenTo(button)
    f.reactions += {
      case ButtonClicked(`button`) => showNewWindow()
    }
    new FlowPanel() {
      border = Swing.EmptyBorder(5,5,5,5)
      contents += button
    }
  }
}


class GameFrame extends Frame {
  title = "Battleships"
  val button = new Button {
    text = "Test Button"
  }
  visible = true
  contents = new BoxPanel(Orientation.Vertical) {
    contents += button
  }
}


object LaunchGame extends SimpleSwingApplication {
  lazy val top: Frame { val demo: LaunchGame } = new Frame() {
    title = "FrameDemo2"
    //Use the Java look and feel.  This needs to be done before the frame is created
    //so the companion object FrameDemo2 cannot simply extend SimpleSwingApplcation.
    try {
      UIManager.setLookAndFeel(
        UIManager.getCrossPlatformLookAndFeelClassName());
    } catch {
      case e: Exception => ;
    }
    //Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);
    //Create and set up the content pane.
    val demo = new LaunchGame()
  }

  val bp: BorderPanel = new BorderPanel() {
    layout(top.demo.createButtonPane(top)) = BorderPanel.Position.South
  }
  top.contents = bp

}



