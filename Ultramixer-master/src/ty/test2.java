package ty;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.JAppletFrame;
import com.jsyn.swing.PortModelFactory;
import com.jsyn.swing.RotaryTextController;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.UnitOscillator;


public class test2 extends JDialog{

	private static final long serialVersionUID = -2704222221111608377L;
	private Synthesizer synth;
	private UnitOscillator osc;
	// Use a square wave to trigger the envelope.
	private UnitOscillator gatingOsc;
	private EnvelopeDAHDSR dahdsr;
	private LineOut lineOut;
	JButton exit1;
	public test2(JFrame frame){
		super(frame);
		setAutoRequestFocus(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		synth = JSyn.createSynthesizer();
		// Add a tone generator.
		synth.add( osc = new SineOscillator() );
		// Add a trigger.
		synth.add( gatingOsc = new SquareOscillator() );
		// Use an envelope to control the amplitude.
		synth.add( dahdsr = new EnvelopeDAHDSR() );
		// Add an output mixer.
		synth.add( lineOut = new LineOut() );
	
		//e/xit1.add();
		gatingOsc.output.connect( dahdsr.input );
		dahdsr.output.connect( osc.amplitude );
		dahdsr.attack.setup( 0.001, 0.01, 2.0 );
		osc.output.connect( 0, lineOut.input, 0 );
		osc.output.connect( 0, lineOut.input, 1 );

		gatingOsc.frequency.setup( 0.001, 0.5, 10.0 );
		gatingOsc.frequency.setName("Rate");

		osc.frequency.setup( 50.0, 440.0, 2000.0 );
		osc.frequency.setName("Freq");
		

		// Arrange the knob in a row.
		setLayout( new GridLayout( 1, 0 ) );

		setupPortKnob( osc.frequency );
		setupPortKnob( gatingOsc.frequency );
		setupPortKnob( dahdsr.attack );
		setupPortKnob( dahdsr.hold );
		setupPortKnob( dahdsr.decay );
		setupPortKnob( dahdsr.sustain );
		setupPortKnob( dahdsr.release );

		validate();
		
	}

	private void setupPortKnob( UnitInputPort port )
	{

		DoubleBoundedRangeModel model = PortModelFactory
				.createExponentialModel( port );
		System.out.println("Make knob for " + port.getName() + ", model.getDV = " + model.getDoubleValue()
				+ ", model.getV = " + model.getValue()
				+ ", port.getV = " + port.get()
				);
		RotaryTextController knob = new RotaryTextController( model, 10 );
		knob.setBorder( BorderFactory.createTitledBorder( port.getName() ) );
		knob.setTitle( port.getName() );
		add( knob );
	}

	public void start()
	{
		// Start synthesizer using default stereo output at 44100 Hz.
		synth.start();
		// We only need to start the LineOut. It will pull data from the
		// oscillator.
		lineOut.start();
		 addWindowListener(new WindowAdapter() {

	            @Override
	            public void windowClosing(WindowEvent e) {
	                System.out.println("closing...");
	                lineOut.stop();
	                synth.stop();
	                
	               
	            }
	        });
		}
	
	
	
	public static void main(String[] args) {
		test2 head = new test2(null);
		head.start();
		head.show();
		
		
		
		
	}
}

