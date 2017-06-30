# Set WKS file depending on the MACHINE picked by the user applies 
# only when using an Intel MACHINE, otherwise leaves it as it is.

python (){
    if d.getVar('MACHINE', True) == "intel-core2-32":
        d.setVar('WKS_FILE', "systemd-bootdisk-tiny32.wks")
    elif d.getVar('MACHINE', True) == "intel-corei7-64":
        d.setVar('WKS_FILE', "systemd-bootdisk-tiny64.wks")
    elif d.getVar('MACHINE', True) == "intel-quark":
        d.setVar('WKS_FILE', "mktinygalileodisk.wks")
}
