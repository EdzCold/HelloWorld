package com.volley.profuturo.en501863.learningproyectv07;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MensajeDialogFragment extends DialogFragment {
	private Button btnAceptar;
	private TextView tvTitulo, tvContenido;
	private btnOkListener listener;
	private String titulo, mensaje;

	@SuppressLint("ValidFragment")
	public MensajeDialogFragment() {
	}

	@SuppressLint("ValidFragment")
	public MensajeDialogFragment(String titulo, String mensaje) {
		this.titulo = titulo;
		this.mensaje = mensaje;
	}

	public interface btnOkListener {
		void okOnClick(DialogFragment dialog);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View vista = inflater.inflate(R.layout.dialog_fragment_mensaje, null);
		iniciarElementosVisuales(vista);
		builder.setView(vista);

		return builder.create();
	}

	public void iniciarElementosVisuales(View vista) {
		btnAceptar = (Button) vista.findViewById(R.id.btnAceptar);

		tvTitulo = (TextView) vista.findViewById(R.id.tvTitulo);
		tvContenido = (TextView) vista.findViewById(R.id.tvContenido);
	}

	public void setListenerAgregarBeneficiario(btnOkListener listener) {
		this.listener = listener;
	}

	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);

		tvTitulo.setText(titulo);
		tvContenido.setText(mensaje);

		btnAceptar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(listener == null)
					getDialog().dismiss();
				else
					listener.okOnClick(MensajeDialogFragment.this);
			}
		});
	}
}
