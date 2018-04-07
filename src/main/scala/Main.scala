import swing._
import event._

object ChatApp extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Chat Shit Get Banged"

    object text extends TextField { columns = 5 }

    val label = new Label {
      text = "default"
    }

    val button = new Button {
      text = "Send"
    }

    contents = new BoxPanel(Orientation.Vertical) {
      contents += text
      contents += button
      contents += label
      border = Swing.EmptyBorder(30,30,10,30)
    }

    listenTo(button)

    var nClicks = 0
    reactions += {
      case ButtonClicked(b) =>
        label.text = text.text
    }

  }
}