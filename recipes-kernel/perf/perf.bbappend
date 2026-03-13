# Fix const qualifier warnings in libbpf.c when building with -Werror
# This is a workaround for older kernel versions (6.12.x) that don't have
# the upstream fix yet. The issue is already fixed in kernel 6.13+.

# remove at next version upgrade or when output changes
PR = "r3"
HASHEQUIV_HASH_VERSION .= ".3"

do_configure:prepend () {
    # Apply libbpf const qualifier fixes
    if [ -e "${S}/tools/lib/bpf/libbpf.c" ]; then
        # Fix kallsyms_cb function - change 'char *res' to 'const char *res'
        sed -i 's/^\(\s*\)char \*res;/\1const char *res;/' "${S}/tools/lib/bpf/libbpf.c"
        # Fix resolve_full_path function - change 'char *next_path' to 'const char *next_path'
        sed -i 's/^\(\s*\)char \*next_path;/\1const char *next_path;/' "${S}/tools/lib/bpf/libbpf.c"
    fi
}
