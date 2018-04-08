
import swing._
import event._

// The main entry point
object LaunchGame extends SimpleSwingApplication {

  // top() is a Swing applications main equivalent. Override it with our custom game launcher and also let it lazy load.
  lazy val top: Frame { val gameLauncher: LaunchGame } = new Frame() {
    title = "Launch Game"
    val gameLauncher = new LaunchGame()
  }

  // Define a new BorderPanel
  val bp: BorderPanel = new BorderPanel() {

    // Create a new FlowPanel from the createButtonPane method on the lazy loaded top Frame above
    val component: Component = top.gameLauncher.createButtonPane(top)

    // Map the component to a constraint
    layout(component) = BorderPanel.Position.South
  }

  // Define the main frame contents
  top.contents = bp
}


// Represents a window from which new games can be launched
class LaunchGame {

  // Creates a new game instance/window
  def showNewWindow() = {
    new GameFrame()
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

// Represents a new game window and instance of which is created by the showNewWindow method
// which is called from a listener on the Launch Game Button
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






